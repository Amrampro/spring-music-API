# Dockerfile for profile-microservice

FROM adoptopenjdk:21-jre-hotspot

WORKDIR /app

COPY target/profile-microservice-0.0.1-SNAPSHOT.jar /app/profile-microservice.jar

EXPOSE 8082

CMD ["java", "-jar", "profile-microservice.jar"]