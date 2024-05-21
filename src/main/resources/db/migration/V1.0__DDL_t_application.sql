CREATE TABLE t_application (
	id uuid NOT NULL,
	reference_num varchar(14) NOT NULL,
	"name" varchar(50) NOT NULL,
	detail jsonb,
	crt_by varchar(50) NOT NULL,
	crt_time timestamp NOT NULL,
	CONSTRAINT t_application_pkey PRIMARY KEY (id)
);