.PHONY: init
init:
	docker-compose up -d

.PHONY: migrate_db
migrate_db:
	gradle :core:flywayMigrate

.PHONY: run_local_api
run_local_api:
	gradle :api:bootRun
