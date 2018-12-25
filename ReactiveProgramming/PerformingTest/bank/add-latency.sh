#!/bin/bash

if [ "$1" = "add" ]
then
    echo "Adding latency to docker0"
    tc qdisc add dev docker0 root netem delay "$2"ms
elif [ "$1" = "del" ]
then
    echo "Removing latency to docker0"
    tc qdisc del dev docker0 root netem
fi

echo "Check latency using $ tc -s qdisc"