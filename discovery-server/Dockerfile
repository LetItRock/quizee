FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/discovery-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/discovery-server.jar"]

EXPOSE 8761