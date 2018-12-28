#!/usr/bin/env bash

docker rm -f account
docker run -d -p 8081:8081 --name account account