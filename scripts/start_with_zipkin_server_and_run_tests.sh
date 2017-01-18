#!/bin/bash

set -e
CURRENT=`pwd`

if [[ ! -e "${CURRENT}/scripts" ]]; then
    . ./start_with_zipkin_server.sh
    . ./run_acceptance_tests.sh
else
    . ./scripts/start_with_zipkin_server.sh
    . ./scripts/run_acceptance_tests.sh
fi
