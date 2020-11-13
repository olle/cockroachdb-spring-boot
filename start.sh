#!/bin/sh

source .env
IMAGE=cockroachdb/cockroach:v20.2.0

docker network create -d bridge ${NETWORK}

docker run -d \
--name=roach1 \
--hostname=roach1 \
--net=${NETWORK} \
-p 26257:26257 -p 9090:8080  \
-v "${PWD}/${DATA}/roach1:/cockroach/${DATA}"  \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker run -d \
--name=roach2 \
--hostname=roach2 \
--net=${NETWORK} \
-v "${PWD}/${DATA}/roach2:/cockroach/${DATA}" \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker run -d \
--name=roach3 \
--hostname=roach3 \
--net=${NETWORK} \
-v "${PWD}/${DATA}/roach3:/cockroach/${DATA}" \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker exec -it roach1 ./cockroach init --insecure

docker exec -it roach1 ./cockroach sql -e "CREATE DATABASE test;" --insecure
