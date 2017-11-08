#!/usr/bin/env bash

mvn clean install && cp -rfv target/*.war /Users/mhewedy/Documents/wildfly-10.1.0.Final/standalone/deployments/
