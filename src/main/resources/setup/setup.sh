#!/bin/bash

# Variables
MYSQL_CONTAINER_NAME="personal-project-container"
MYSQL_ROOT_PASSWORD="bala123$"   # Set your root password here
MYSQL_DATABASE="bank_db"   # Set your desired database name here
MYSQL_PORT="3306"

# Check if Docker is installed
if ! [ -x "$(command -v docker)" ]; then
  echo "Error: Docker is not installed. Please install Docker and try again." >&2
  exit 1
fi

# Check if the container is already running
if [ $(docker ps -q -f name=${MYSQL_CONTAINER_NAME}) ]; then
  echo "MySQL container is already running."
else
  # Check if the container exists but is not running
  if [ $(docker ps -aq -f status=exited -f name=${MYSQL_CONTAINER_NAME}) ]; then
    echo "Starting existing MySQL container..."
    docker start ${MYSQL_CONTAINER_NAME}
  else
    # Pull the MySQL image if not already available
    echo "Pulling MySQL Docker image..."
    docker pull mysql:latest

    # Run the MySQL container
    echo "Creating and starting a new MySQL container..."
    docker run --name ${MYSQL_CONTAINER_NAME} \
      -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} \
      -e MYSQL_DATABASE=${MYSQL_DATABASE} \
      -p ${MYSQL_PORT}:3306 \
      -d mysql:latest

    echo "MySQL container setup complete."
  fi
fi

# Show the running containers
docker ps