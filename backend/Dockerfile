FROM maven:latest AS build
WORKDIR /app/backend
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17

COPY --from=build /app/backend/target/backend-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT [ "java", "-jar", "backend.jar" ]