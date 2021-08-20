# SpringOne Message Board Service

## Run tests
```shell
./mvnw clean install -U -DfailOnInProgress=false
```

## Run app

#### 1. Start the DB
> Note: Choose one of the two options below: Docker or Kubernetes

**Run Bitnami PostgresDB as a docker container**
```shell
./bin/run_db.sh
```

**Install Bitnami PostgresDB to Kubernetes**
```shell
helm repo add bitnami https://charts.bitnami.com/bitnami

helm install postgres-db --set postgresqlPassword=iu4w78hj,postgresqlDatabase=message-board bitnami/postgresql

kubectl port-forward --namespace default svc/postgres-db-postgresql 5432:5432 &

PGPASSWORD="iu4w78hj" psql --host 127.0.0.1 -U postgres -d message-board -p 5432
```

#### 2. Start app
```shell
./mvnw spring-boot:run -U -DfailOnInProgress=false
```

#### 3. Send requests

```shell
# Invalid message (lowercase username)
curl -X 'POST' \
        http://localhost:8080/message \
        -H 'accept: application/json' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "marygabry",
        "text": "Prego"
        }'
```

```shell
# Valid message (uppercase username)
curl -X 'POST' \
        http://localhost:8080/message \
        -H 'accept: application/json' \
        -H 'Content-Type: application/json' \
        -d '{
        "username": "Gabry",
        "text": "Ciao"
        }'
```

```shell
# Get all messages
curl -X 'GET' \
  http://localhost:8080/message \
  -H 'accept: application/json'
```

```shell
# Delete by username
curl -X 'DELETE' \
  http://localhost:8080/message/Gabry \
  -H 'accept: application/json'
```
