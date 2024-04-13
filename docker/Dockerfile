FROM maven:3.8.7 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"]

