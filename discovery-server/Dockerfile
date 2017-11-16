FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/discovery-server.jar /app/
ADD ./init.sh /init.sh

RUN \
 chmod +x /init.sh && \
 apk update && \
 apk upgrade && \
 apk add --no-cache bash && \
 apk add --no-cache curl

ENTRYPOINT ["/init.sh"]

EXPOSE 8761