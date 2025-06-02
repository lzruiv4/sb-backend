package com.lam.sb_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        // for angular
                        "http://localhost:4200",
                        "http://192.168.0.160:4200",
                        // for uni-app
                        "http://localhost:5173",
                        // for vue
                        "http://localhost:9000",
                        "http://192.168.0.160:9000"
                )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
