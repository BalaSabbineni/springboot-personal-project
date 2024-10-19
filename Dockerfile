FROM openjdk:21-jdk

# Copy the JAR file from the target directory of your project
COPY target/personalProject-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application listens on (e.g., 8080)
EXPOSE 8080

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]