# Template-API

## setup gradle

```sh

$ gradle clean
$ gradle build
$ gradle wrapper

```

## setup database

```sh

$ make migrate_db

```

## run with development options

```sh

$ docker-compose up -d
$ make run_local_api

```

## run with production options

```sh

$ DB_URL=jdbc:postgresql://localhost:5432/template_api DB_USER=postgres DB_PASSWORD=postgres RELEASE_VERSION=1.0.0 ./gradlew :api:bootRun --args='--spring.profiles.active=prod'

```
