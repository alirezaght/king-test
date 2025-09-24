# Build stage
FROM gradle:8.4-jdk17 AS build

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle build --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/king-backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]