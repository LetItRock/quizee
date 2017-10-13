FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/auth-server.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/auth-server.jar"]

EXPOSE 8000