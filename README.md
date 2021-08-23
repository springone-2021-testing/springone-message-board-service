# SpringOne Message Board Service

## Install contracts to local maven repository
Run the following commands.
The script will install the stubs jar file to your local maven repository.
At the prompt, enter the option for the desired version of `springone-message-board-contracts`.
```shell
git clone https://github.com/springone-2021-testcontainers/api-contracts temp/api-contracts
./temp/api-contracts/bin/install-stubs-to-local-maven-repo.sh
rm -rf temp/api-contracts
```

## Run tests
> Note:
> The setting "-Dcontracts.version=+" will use the latest contracts in the local maven repository.
> If appropriate, replace the "+" with a specific version number.
```shell
./mvnw clean install -U -DfailOnInProgress=false -Dcontracts.version=+
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
