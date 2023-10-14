# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your local machine into the container at /app
COPY target/*.jar /app/app.jar

# Expose the port that your Spring Boot application will run on (default is 8080)
EXPOSE 9090

# Define the command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]