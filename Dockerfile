# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/employee-api.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/employee-api.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
