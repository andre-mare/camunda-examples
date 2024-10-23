echo "\nDeploy Personal Message Process V1"
curl --location http://localhost:8080/engine-rest/deployment/create -v -F "deployment-name=send-personal-message" -F "deployment-source=local" -F "data=@send-personal-message-v2.bpmn" -F "enable-duplicate-filtering=true" -F "deploy-changed-only=true"

