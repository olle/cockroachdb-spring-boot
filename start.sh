#!/bin/sh

source .env


docker network create -d bridge ${NETWORK}

IMAGE=cockroachdb/cockroach:v20.2.0

docker run -d \
--name=roach1 \
--hostname=roach1 \
--net=${NETWORK} \
-p 26257:26257 -p 9090:8080  \
-v "${PWD}/cockroach-data/roach1:/cockroach/cockroach-data"  \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker run -d \
--name=roach2 \
--hostname=roach2 \
--net=${NETWORK} \
-v "${PWD}/cockroach-data/roach2:/cockroach/cockroach-data" \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker run -d \
--name=roach3 \
--hostname=roach3 \
--net=${NETWORK} \
-v "${PWD}/cockroach-data/roach3:/cockroach/cockroach-data" \
${IMAGE} start \
--insecure \
--join=roach1,roach2,roach3

docker exec -it roach1 ./cockroach init --insecure