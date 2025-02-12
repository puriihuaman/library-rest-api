FROM openjdk:17-jdk-alpine
COPY target/library-rest-api-0.0.1-SNAPSHOT.jar library-rest-api.jar
ENTRYPOINT ["java", "-jar", "library-rest-api.jar"]
