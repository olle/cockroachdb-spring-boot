CockroachDB - Spring Boot
=========================

An example project, for trying out and presenting an example-setup of Spring
Boot and CockroachDB.

Getting started
---------------

A local three node CockroachDB cluster can be started using Docker. The script
`start.sh` will ensure that the cluster is booted up, initialized and that a
database `test` is created.

The Spring Boot application can be started using the Maven wrapper. Running
`./mvnw spring-boot:run` will compile and run the app. The application is
then available at `http://localhost:8080`.

To shut down and remove the CockroachDB cluster, run the `stop.sh` script.
