#!/bin/bash

# Build whole project
mvn clean package -Dmaven.test.skip=true

# Remove dangling images <none>:<none>
docker rmi $(docker images -f "dangling=true" -q)

# Build docker images
# docker build -t letitrock/quizee-account-service ./account-service
# docker build -t letitrock/quizee-auth-server ./auth-server
docker build -t letitrock/quizee-config-server ./config-server
docker build -t letitrock/quizee-discovery-server ./discovery-server
docker build -t letitrock/quizee-gateway-service ./gateway-service
# docker build -t letitrock/quizee-quiz-service ./quiz-service

# Run docker
docker-compose -f docker-compose.yml -f docker-compose-dev.yml up