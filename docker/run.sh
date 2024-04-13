#!/bin/bash

docker-compose down

git pull

echo "Starting Docker containers..."
docker-compose up -d --build

sleep 5

echo "Checking status of containers..."
docker-compose ps
