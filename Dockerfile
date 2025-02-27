# Étape 1 : Construire le projet avec Maven
FROM maven:3.8.6-openjdk-11-slim AS build

WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le reste du projet et compiler le fichier JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image finale avec le JAR construit
FROM openjdk:11-jre-slim

WORKDIR /app

# Copier le fichier JAR de l'étape précédente
COPY --from=build /app/target/*.jar /app.jar

# Exposer le port 8080
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
