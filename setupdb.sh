#!/bin/bash
echo Wait for servers to be up
sleep 10

HOSTPARAMS="--host db-1 --insecure"
CMD="/cockroach/cockroach.sh"
SQL="$CMD sql $HOSTPARAMS"

$CMD user set maxroach $HOSTPARAMS
$SQL -e "CREATE DATABASE test;"
$SQL -e 'GRANT ALL ON DATABASE test TO maxroach'
