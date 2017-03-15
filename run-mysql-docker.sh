#!/usr/bin/env bash

# This file used to run mysql server on docker

docker run --name mysql \
    -e 'MYSQL_ALLOW_EMPTY_PASSWORD=true' -e 'MYSQL_DATABASE=test' \
    -p 3307:3306 \
    -d mysql:latest