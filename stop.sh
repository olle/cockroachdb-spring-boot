#!/bin/sh

source .env

docker stop roach1 roach2 roach3
docker rm roach1 roach2 roach3
docker network rm ${NETWORK}
