FROM openjdk:11
WORKDIR /app

# Copier juste le pom.xml pour installer les dépendances
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:resolve

# Copier uniquement le code source (sans target ni fichiers sensibles)
COPY src/ ./src/

# Build de l'application
RUN mvn clean package -DskipTests

# Copier le JAR final dans le conteneur
COPY target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
