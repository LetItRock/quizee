FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/auth-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/auth-server.jar"]

EXPOSE 8000