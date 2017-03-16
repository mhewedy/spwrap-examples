#!/usr/bin/env bash

# This file used to run mysql server on docker

docker run --name mysql \
    -e 'MYSQL_ALLOW_EMPTY_PASSWORD=true' -e 'MYSQL_DATABASE=test' \
    -p 3307:3306 \
    -d mysql:latest

docker run --name oracle \
    -e 'ORACLE_ALLOW_REMOTE=true' \
    -p 1522:1521 \
    -d wnameless/oracle-xe-11g
