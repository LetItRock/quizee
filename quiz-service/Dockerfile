FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/quiz-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/quiz-service.jar"]

EXPOSE 8100