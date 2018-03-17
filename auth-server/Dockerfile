FROM openjdk:8-jdk-alpine
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD ./target/auth-server.jar /app/
ADD ./init.sh /init.sh

RUN \
 chmod +x /init.sh && \
 apk update && \
 apk upgrade && \
 apk add --no-cache bash && \
 apk add --no-cache curl

# DEBUG
ENTRYPOINT ["/init.sh"]
EXPOSE 8000
# EXPOSE 5001