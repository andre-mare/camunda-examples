echo "\nDeploy Personal Message Process V1"
curl --location http://localhost:8080/engine-rest/deployment/create -v -F "deployment-name=send-personal-message" -F "deployment-source=local" -F "data=@send-personal-message-v1.bpmn" -F "enable-duplicate-filtering=true" -F "deploy-changed-only=true"

echo "\nStart Personal Message Process V1"

number_of_instances=100
# Example for loop
for ((counter=1; counter<=number_of_instances; counter++))
do
  curl --location --request POST 'http://localhost:8080/engine-rest/process-definition/key/send-personal-message/start' --header 'Content-Type: application/json' --data-raw '{}'
done