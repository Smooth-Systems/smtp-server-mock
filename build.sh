#!/bin/bash

set -e

mvn clean package
docker build . -t smtp-sink
docker run -p 1025:10025 -p 8010:8010 --name smtp-sink smtp-sink
#docker run --rm -p 1025:10025 -p 8010:8010 --name smtp-sink smtp-sink

# docker exec -it smtp-sink bash

# docker rm smtp-sink