FROM eclipse-temurin:17-jdk-alpine
COPY target/manager-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]