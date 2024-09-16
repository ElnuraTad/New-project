FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/Megacom-0.0.1-SNAPSHOT.jar app.jar


ENV SPRING_REDIS_HOST=redis
ENV SPRING_REDIS_PORT=6379

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "app.jar"]