#!/bin/bash

rm -fR build
gradle build

docker build -t richardbrandao/estudos:sns-sqs-example .
docker push richardbrandao/estudos:sns-sqs-example