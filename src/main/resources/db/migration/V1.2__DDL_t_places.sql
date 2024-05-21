CREATE TABLE t_places (
	id uuid NOT NULL,
	place_id varchar(255) NOT NULL,
	address varchar(255) NOT NULL,
	latitude numeric(9,4) NOT NULL,
	longitude numeric(9,4) NOT NULL,
	crt_time timestamp NOT NULL,
	CONSTRAINT t_places_pkey PRIMARY KEY (id)
);