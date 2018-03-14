#!/bin/bash

url="http://config-server:8888/health";
while [ $(curl -s -o /dev/null -I -w "%{http_code}" "$url") != 200 ]; do
sleep 1;
done

JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5003 -Djava.security.egd=file:/dev/./urandom";
java $JAVA_OPTS -Xmx200m -jar /app/quiz-service.jar

# java -Xmx200m -jar /app/quiz-service.jar