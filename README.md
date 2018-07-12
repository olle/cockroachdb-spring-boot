CockroachDB - Spring Boot
=========================

A small project for trying out app-development with Spring Boot and CockroachDB,
as DB.

Getting started
---------------

Simply run `make`. Requires Docker as it is now. The CockroachDB UI will be on
`http://localhost:8080` and the Spring Boot app on `http://localhost:9090`.


Notes and lessons learned
-------------------------

* Tried to manage schema with Liquibase, did not quite work out. Open issue
  for missing capabilities - which is ok. Leaving this here.

  https://github.com/cockroachdb/cockroach/issues/26737


