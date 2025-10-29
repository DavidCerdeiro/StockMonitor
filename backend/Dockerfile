# Stage 1: Build - Usando una imagen que tiene Maven y JDK 21
# ---- ESTA LÍNEA ES LA QUE CAMBIA ----
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia primero el pom.xml para cachear las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código fuente y construye la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run - Usando una imagen JRE ligera (Esto se queda igual)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Expone el puerto en el que corre Spring Boot
EXPOSE 8080

# Copia el .jar construido del stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]