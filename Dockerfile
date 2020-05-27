FROM openjdk:11-jre-slim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} employee-api.jar

ENTRYPOINT ["java", "-jar", "/employee-api.jar"]
EXPOSE 8080


