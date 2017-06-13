#!/bin/sh
grails war
docker build . -t aoiat/rainer
