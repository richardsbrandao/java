#!/usr/bin/env bash

docker rm -f creditcard
docker run -d -p 8082:8082 --name creditcard creditcard