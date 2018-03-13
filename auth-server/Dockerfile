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

ENTRYPOINT ["/init.sh"]

EXPOSE 8000

# DEBUG
# ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5001 -Djava.security.egd=file:/dev/./urandom"
# ENTRYPOINT ["/init.sh $JAVA_OPTS"]
# EXPOSE 8200
# EXPOSE 5001