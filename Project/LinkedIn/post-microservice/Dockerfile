# Dockerfile for post-service

FROM adoptopenjdk:21-jre-hotspot

WORKDIR /app

COPY target/post-microservice-0.0.1-SNAPSHOT.jar /app/post-microservice.jar

EXPOSE 8083

CMD ["java", "-jar", "post-microservice.jar"]