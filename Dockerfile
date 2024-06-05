FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY src/ src

COPY pom.xml ./

RUN mvn package -DskipTests

FROM openjdk:22

WORKDIR /app

COPY --from=builder /app/target/JavaTechnicalChallenge-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
