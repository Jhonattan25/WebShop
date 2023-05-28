#!/bin/bash
docker run --rm  --volume $1:/opt/keycloak/data --volume $(pwd):/h2 ubuntu tar xvf ./h2/$2 -C /opt/keycloak/data --strip 1