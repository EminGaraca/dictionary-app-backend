FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim-sid
WORKDIR /app
COPY --from=build /app/target/dictionary-0.1.1-SNAPSHOT.jar ./dictionary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dictionary.jar"]
