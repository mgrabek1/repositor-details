FROM eclipse-temurin:17-jdk-alpine
COPY target/repository-details-0.0.1-SNAPSHOT.jar repository-details-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/repository-details-0.0.1-SNAPSHOT.jar"]