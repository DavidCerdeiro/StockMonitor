# --- Etapa 1: Construcción del Proyecto ---
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar el cache de Docker.
# Si las dependencias no cambian, Docker no las volverá a descargar.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Ejecutamos el empaquetado de Maven para crear el archivo .jar.
# -DskipTests omite la ejecución de tests para acelerar la construcción.
RUN mvn clean package -DskipTests


# --- Etapa 2: Ejecución de la Aplicación ---
FROM eclipse-temurin:21-jdk-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo .jar que se generó en la etapa de 'build'
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080, que es el puerto por defecto de Spring Boot,
# para que Render pueda redirigir el tráfico hacia él.
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor se inicie.
# Simplemente ejecuta la aplicación Java contenida en el .jar.
ENTRYPOINT ["java","-jar","app.jar"]

