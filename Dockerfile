FROM openjdk:17-alpine

WORKDIR /app

COPY target/springboot-mongo-docker.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]