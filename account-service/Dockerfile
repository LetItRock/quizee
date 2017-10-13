FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/account-service.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/account-service.jar"]

EXPOSE 8200