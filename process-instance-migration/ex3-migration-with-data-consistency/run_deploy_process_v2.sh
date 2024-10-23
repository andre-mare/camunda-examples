echo "\nDeploy Personal Message Process V1"
curl --location http://localhost:8080/engine-rest/deployment/create -v -F "deployment-name=motor-finance-application" -F "deployment-source=local" -F "data=@motor-finance-application-v2.bpmn" -F "enable-duplicate-filtering=true" -F "deploy-changed-only=true"

