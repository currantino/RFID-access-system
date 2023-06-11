#!/bin/bash

mvn clean package -DskipTests
docker-compose build --no-cache
docker-compose up --force-recreate
