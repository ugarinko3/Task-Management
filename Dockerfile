FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Management-0.0.1-SNAPSHOT.jar app.jar


ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
