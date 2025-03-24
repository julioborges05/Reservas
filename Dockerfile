FROM amazoncorretto:21.0.4-alpine3.18

WORKDIR /app

ENV DB_URL=${DB_URL}

COPY target/reservas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]