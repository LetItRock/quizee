FROM openjdk:jre-8
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/config-server.jar /quizee/
CMD ["java", "-Xmx200m", "-jar", "/quizee/config-server.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/health || exit 1

EXPOSE 8888