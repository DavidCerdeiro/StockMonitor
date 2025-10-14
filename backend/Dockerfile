# --- Etapa 1: Construcción del Proyecto ---
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests


# --- Etapa 2: Ejecución de la Aplicación ---
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

# SIN perfil prod, las variables de entorno se usan automáticamente
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dserver.address=0.0.0.0 -jar app.jar"]
