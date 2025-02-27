# Étape 1 : Build (multi-stage build)
FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /app

# Copier le pom.xml pour résoudre les dépendances Maven
COPY pom.xml .

# Résoudre les dépendances Maven sans copier tout le code source
RUN mvn dependency:resolve

# Copier le code source après avoir résolu les dépendances
COPY src/ ./src/

# Build de l'application
RUN mvn clean package -DskipTests

# Étape 2 : Image finale
FROM openjdk:11-jre-slim

# Copier le JAR généré depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Commande pour démarrer l'application
CMD ["java", "-jar", "app.jar"]
