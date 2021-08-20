# SpringOne Message Board Service


### To test with contracts in progress:
./mvnw clean install -U -DfailOnInProgress=false

### To run with contracts in progress:
./mvnw spring-boot:run -U -DfailOnInProgress=false

### Install PostgressDB using Bitnami:
helm repo add bitnami https://charts.bitnami.com/bitnami

helm install postgres-db --set postgresqlPassword=iu4w78hj,postgresqlDatabase=message-board bitnami/postgresql

kubectl port-forward --namespace default svc/postgres-db-postgresql 5432:5432 &

PGPASSWORD="iu4w78hj" psql --host 127.0.0.1 -U postgres -d message-board -p 5432