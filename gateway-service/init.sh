#!/bin/bash

url="http://config-server:8888/health";
while [ $(curl -s -o /dev/null -I -w "%{http_code}" "$url") != 200 ]; do
sleep 1;
done

java -Xmx200m -jar /app/gateway-service.jar