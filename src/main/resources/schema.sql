CREATE TABLE IF NOT EXISTS stuff
	(
		id STRING(64) NOT NULL,
		suffix STRING(6) NOT NULL,
		mime_type STRING(32) NULL,
		CONSTRAINT pk_stuff PRIMARY KEY (id ASC),
		UNIQUE INDEX stuff_id_key (id ASC),
	FAMILY "primary" (id, suffix, mime_type)
);
