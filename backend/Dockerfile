# --- Etapa 1: Construcción del Proyecto ---
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar el cache de Docker.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Ejecutamos el empaquetado de Maven para crear el archivo .jar.
RUN mvn clean package -DskipTests


# --- Etapa 2: Ejecución de la Aplicación ---
FROM eclipse-temurin:21-jdk-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo .jar que se generó en la etapa de 'build'
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto que Render espera (normalmente usa la variable PORT)
# pero mantenemos 8081 como tu aplicación lo tiene configurado
EXPOSE 8081

# Activamos el perfil de producción y configuramos el servidor
ENTRYPOINT ["java", \
            "-Dspring.profiles.active=prod", \
            "-Dserver.address=0.0.0.0", \
            "-jar", \
            "app.jar"]