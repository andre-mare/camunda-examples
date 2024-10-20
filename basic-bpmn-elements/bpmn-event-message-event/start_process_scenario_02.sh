#!/bin/bash

echo "Scenario 2: User Task 1 --> Message --> User Task 2 --> End"

echo Message Event Process: Start Process
curl --location --request POST 'http://localhost:8080/engine-rest/message' --header 'Content-Type: application/json' --data-raw '{
     "messageName": "message-start-event",
     "businessKey": "process-key-123"
}'

sleep 5

echo Message Event Process: Complete User Task 1
ID=$(curl --location --fail --silent --request POST 'http://localhost:8080/engine-rest/task' --header 'Content-Type: application/json' --data-raw '{ "processInstanceBusinessKey": "process-key-123", "taskDefinitionKey": "user-task-1"}' | jq -r '.[0].id')

curl --location --fail --silent --request POST "http://localhost:8080/engine-rest/task/$ID/complete" --header 'Content-Type: application/json'  --data-raw '{}'

sleep 5

echo Message Event Process: Recieve Message
curl --location --request POST 'http://localhost:8080/engine-rest/message' --header 'Content-Type: application/json' --data-raw '{
     "messageName": "message-intermediate-catch-event",
     "businessKey": "process-key-123"
}'

sleep 5

echo Message Event Process: Complete User Task 2
ID=$(curl --location --fail --silent --request POST 'http://localhost:8080/engine-rest/task' --header 'Content-Type: application/json' --data-raw '{ "processInstanceBusinessKey": "process-key-123", "taskDefinitionKey": "user-task-2"}' | jq -r '.[0].id')

curl --location --fail --silent --request POST "http://localhost:8080/engine-rest/task/$ID/complete" --header 'Content-Type: application/json' --data-raw '{}'