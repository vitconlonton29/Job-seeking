#!/bin/bash

docker-compose down

sleep 5

echo "Checking status of containers..."
docker-compose ps
