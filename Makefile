.PHONY: run

run:
	docker-compose up -d
	mvn clean spring-boot:run
