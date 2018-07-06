.PHONY: run

run:
	docker-compose up -d
	sleep 6
	mvn clean spring-boot:run

clean:
	docker-compose down
	rm -rf data/
