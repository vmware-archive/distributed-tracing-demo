#!/bin/bash

kill `jps | grep "1.0.0.SLEUTH_DOCS.jar" | cut -d " " -f 1`