#!/bin/sh

source .env

##
## CockroachDB Cluster
##
IMAGE=cockroachdb/cockroach:v20.2.0
CLUSTER=roach1,roach2,roach3

docker network create -d bridge ${NETWORK}

docker run -d \
--name=roach1 \
--hostname=roach1 \
--net=${NETWORK} \
-p 26257:26257 -p 9090:8080  \
-v "${PWD}/${DATA}/roach1:/cockroach/cockroach-data"  \
${IMAGE} start \
--insecure \
--join=${CLUSTER}

for i in {2..3};
do
  docker run -d \
    --name="roach${i}" \
    --hostname="roach${i}" \
    --net=${NETWORK} \
    -v "${PWD}/${DATA}/roach${i}:/cockroach/cockroach-data" \
    ${IMAGE} start \
    --insecure \
    --join=${CLUSTER}
done

docker exec -it roach1 ./cockroach init --insecure
docker exec -it roach1 ./cockroach sql -e "CREATE DATABASE test;" --insecure

##
## PostgreSQL
##
docker run -d \
--name=postgresql \
-p 5432:5432 \
-e POSTGRES_USER=sa \
-e POSTGRES_PASSWORD=root \
-e POSTGRES_DB=test \
-v "${PWD}/${DATA}/postgres:/var/lib/postgresql" \
postgres
