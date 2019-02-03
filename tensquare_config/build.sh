#!/usr/bin/env bash
#只能说思路把，但是docker 命令不能用
docker login 192.168.35.130:5000 -u admin -p Harbor12345
mvn clean package docker:build -DpushImage