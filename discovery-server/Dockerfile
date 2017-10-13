FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/discovery-server.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/discovery-server.jar"]

EXPOSE 8761