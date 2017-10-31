FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/gateway-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service.jar"]

EXPOSE 4000