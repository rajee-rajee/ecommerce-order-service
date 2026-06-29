# Use Java 25 base image
FROM eclipse-temurin:25-jdk

# Set working directory
WORKDIR /app

# Copy Spring Boot JAR into container
COPY target/*.jar app.jar

# Application runs on port 8080
EXPOSE 8080

# Start the application
ENTRYPOINT ["java","-jar","app.jar"]