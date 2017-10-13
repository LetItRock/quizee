FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/gateway-service.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/gateway-service.jar"]

EXPOSE 4000