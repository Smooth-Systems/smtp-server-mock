#!/bin/bash

set -e

mvn clean package
docker build . -t smoothsystemssolutions/smtp-server-mock
#docker run -p 1025:10025 -p 8010:8010 --name smtp-server-mock smooth-systems-solutions/smtp-server-mock
#docker run --rm -p 1025:10025 -p 8010:8010 --name smtp-sink smooth-systems-solutions/smtp-sink

# docker exec -it smtp-sink bash

# docker rm smtp-sink
