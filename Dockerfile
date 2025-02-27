# Étape 1: Utiliser une image de base avec JDK 11
FROM openjdk:11-jre-slim

# Étape 2: Ajouter le fichier JAR à l'image Docker
COPY target/*.jar app.jar

# Étape 3: Exposer le port sur lequel l'application écoutera
EXPOSE 8080

# Étape 4: Lancer l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
