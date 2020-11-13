#!/bin/sh

source .env

docker stop roach1 roach2 roach3 postgresql
docker rm roach1 roach2 roach3 postgresql
docker network rm ${NETWORK}
rm -rf ./${DATA}
