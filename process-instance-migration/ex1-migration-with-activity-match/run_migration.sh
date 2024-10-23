#!/bin/bash

curl --location --request GET 'localhost:8080/migrate' \
--header 'Content-Type: application/json' \
--data-raw ''