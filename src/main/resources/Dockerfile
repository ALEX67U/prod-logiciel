FROM openjdk:11
WORKDIR /app

# Build de l'application (ça va créer le fichier JAR dans target/)
COPY . .
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Copy du JAR vers l'image finale
COPY target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
