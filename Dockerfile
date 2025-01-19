FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/ESCBackend-0.0.1-SNAPSHOT.jar back.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","back.jar"]