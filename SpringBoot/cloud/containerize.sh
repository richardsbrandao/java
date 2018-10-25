#!/bin/bash

rm -fR build
./gradlew build

docker build -t richardbrandao/estudos:sns-sqs-example .
docker push richardbrandao/estudos:sns-sqs-example