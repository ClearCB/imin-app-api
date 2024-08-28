# the base image
FROM maven:3.9.6 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image with Java installed
FROM openjdk:21-jdk

# Set the working directory in the   container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
