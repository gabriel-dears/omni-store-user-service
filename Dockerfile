# ----------- STAGE 1: BUILD -----------
FROM gradle:8.7-jdk21 AS builder

# Enable gradle cache
ENV GRADLE_USER_HOME=/home/gradle/.gradle

# Create app directory and copy project files
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradle/ ./

RUN gradle build -x test --no-daemon || true

COPY src ./src

# Build the application
RUN gradle bootJar --no-daemon


# ----------- STAGE 2: RUNTIME -----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the fat JAR from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port used by the app
EXPOSE 8081
EXPOSE 5005

# Run the app
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
