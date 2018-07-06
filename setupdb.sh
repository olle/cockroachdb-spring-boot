#!/bin/bash
echo Wait for servers to be up
sleep 10

HOSTPARAMS="--host db-1 --insecure"
SQL="/cockroach/cockroach.sh sql $HOSTPARAMS"

$SQL -e "CREATE DATABASE test;"
