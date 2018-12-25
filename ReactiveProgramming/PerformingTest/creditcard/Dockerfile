FROM java:8
RUN mkdir /app
WORKDIR /app
COPY build/libs/creditcard-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]