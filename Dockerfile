# Use the official OpenJDK 26 image as the base image
FROM eclipse-temurin:26-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine into the container at /app
COPY target/*.jar app.jar

# Expose the port that Spring Boot listens on inside the container
EXPOSE 8080

# Define the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
