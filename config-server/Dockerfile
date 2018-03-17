FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx512m", "-jar", "/app/config-server.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/health || exit 1

EXPOSE 8888