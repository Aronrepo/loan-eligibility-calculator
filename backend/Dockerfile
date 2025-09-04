# Dockerfile
FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /backend

# copy POM and fetch dependencies (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# copy source & build
COPY . .
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jdk

COPY --from=backend-build /backend/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]