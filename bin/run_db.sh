#!/usr/bin/env bash

docker run  -d --rm --name postgres \
            -p 5432:5432 \
            -e POSTGRES_USER=springone \
            -e PGUSER=springone \
            -e POSTGRES_PASSWORD=springone \
            postgres:latest

docker ps