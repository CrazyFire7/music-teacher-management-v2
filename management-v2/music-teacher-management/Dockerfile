FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/api/target/api-*.jar app.jar
COPY --from=build /app/api/src/main/resources/templates /app/templates

RUN mkdir -p /app/temp && chmod -R 777 /app/temp

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]