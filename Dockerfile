FROM openjdk:17-slim

WORKDIR /app

COPY build/libs/sb_backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT [ "java", "-jar", "app.jar" ]