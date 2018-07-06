.PHONY: run

run:
	docker-compose up -d
	sleep 3
	mvn clean spring-boot:run

clean:
	docker-compose down
	rm -rf data/
