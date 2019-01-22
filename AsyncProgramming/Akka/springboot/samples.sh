#!/bin/bash

curl -X POST -H "Content-Type: application/json" --data '{"name": "Harry Potter I", "duration": 5400}' http://localhost:8080/movies
curl -X POST -H "Content-Type: application/json" --data '{"name": "Dragon Ball Super Broly", "duration": 5478}' http://localhost:8080/movies

curl http://localhost:8080/movies

