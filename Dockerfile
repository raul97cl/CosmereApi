FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} CosmereApi.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/CosmereApi.jar"]
