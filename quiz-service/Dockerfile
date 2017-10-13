FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/quiz-service.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/quiz-service.jar"]

EXPOSE 8100