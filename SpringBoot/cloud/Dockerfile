FROM openjdk:8-jdk-alpine

RUN mkdir /app
COPY /build/libs/cloud-1.0.0.jar /app
WORKDIR /app
CMD ["java", "-jar", "cloud-1.0.0.jar"]