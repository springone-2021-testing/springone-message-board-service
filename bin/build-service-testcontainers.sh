#!/usr/bin/env bash

docker build k8s/ --file k8s/Dockerfile --tag docker
docker tag docker gcr.io/fe-ciberkleid/springone/docker
docker push gcr.io/fe-ciberkleid/springone/docker

kubectl create ns springone-message-board
kubectl config set-context --current --namespace=springone-message-board

#kubectl create deployment docker --image=ciberkleid/docker \
#        --dry-run=client --output yaml > k8s/docker-deployment.yaml

kubectl expose -f k8s/docker-deployment.yaml --name docker \
        --protocol=TCP --port=80 --target-port=80 \
        --type=LoadBalancer \
        --dry-run=client --output yaml > k8s/docker-service.yaml

kp image create springone-message-board-service \
  --tag gcr.io/fe-ciberkleid/springone/springone-message-board-service \
  --cluster-builder base \
  --namespace springone-message-board \
  --env BP_MAVEN_BUILD_ARGUMENTS='package' \
  --env DOCKER_HOST=tcp://docker:80 \
  --wait \
  --git https://github.com/springone-2021-testing/springone-message-board-service \
  --git-revision main \
  --dry-run --output  yaml > k8s/image.yaml

kubectl apply -f k8s/docker-deployment.yaml
kubectl apply -f k8s/docker-service.yaml

kp secret create regcred --gcr /Users/ciberkleid/Downloads/fe-ciberkleid-c2db4d4e8708.json
kubectl apply -f k8s/image.yaml

kp build logs springone-message-board-service -b 1

#kubectl delete -f k8s/
#kubectl delete secret regcred
#kubectl delete ns springone-message-board
#kp image delete springone-message-board-service