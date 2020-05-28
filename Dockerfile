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

###### one more CONFIGURATION with JDK builded at container ######
#FROM openjdk:11-jdk as build
#WORKDIR /workspace/app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM openjdk:11-jdk
#VOLUME /tmp
#ARG DEPENDENCY=/worspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib        /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF            /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes    /app
#ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.ukeess.EmployeeApiApplication"]
#EXPOSE 8080
