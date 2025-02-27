# Étape 1: Utiliser une image Java de base
FROM openjdk:11-jre-slim

# Étape 2: Copier le fichier JAR dans l'image Docker
COPY target/*.jar /app.jar

# Étape 3: Exposer le port 8080
EXPOSE 8080

# Étape 4: Exécuter l'application Java
ENTRYPOINT ["java", "-jar", "/app.jar"]
