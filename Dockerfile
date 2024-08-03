# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
#ADD target/spring-boot-docker.jar spring-boot-docker.jar
COPY target/TaskManagement-0.0.1-SNAPSHOT.jar spring-boot-docker.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]
