#!/usr/bin/env bash

mvn clean install && cp -rfv target/*.war ~/Work/wildfly-10/standalone/deployments/

