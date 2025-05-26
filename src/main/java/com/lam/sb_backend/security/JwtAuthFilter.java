package com.lam.sb_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    @Lazy
    private RoleService roleService;

    /**
     * HttpServletRequest request ：封装客户端发送的请求信息（URL、参数、头信息等）。
     * HttpServletResponse response ：用于给客户端返回响应。
     * FilterChain filterChain ：过滤器链，允许请求继续往下传递给后续的过滤器或者最终的资源
     * */
    @Override
    protected void doFilterInternal(HttpServletRequest requestFromClient,
                                    HttpServletResponse responseToClient,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = requestFromClient.getRequestURI();

        // Permit requests if their paths include the following
        if (path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.equals("/swagger-ui.html")
                || path.startsWith("/api/auth")) {
            filterChain.doFilter(requestFromClient, responseToClient);
            return;
        }

        try {
            String token = null;

            // try to get Authorization Header from token
            String authHeader = requestFromClient.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }

            // Option：try to get token from cookie
            if (token == null) {
                Cookie[] cookies = requestFromClient.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("token".equals(cookie.getName())) {
                            token = cookie.getValue();
                            break;
                        }
                    }
                }
            }

            // if token is still missing, proceed with filter chain for anonymous access
            if (token == null) {
                filterChain.doFilter(requestFromClient, responseToClient);
                return;
            }

            // Parse the token and check its validity
            String username = jwtService.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = roleService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(requestFromClient));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // call it at the end
            filterChain.doFilter(requestFromClient, responseToClient);

        } catch (Exception e) {
            responseToClient.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseToClient.getWriter().write("Invalid JWT token: " + e.getMessage());
        }
    }
}
