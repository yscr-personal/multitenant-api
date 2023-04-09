CREATE TABLE IF NOT EXISTS data_source_configurations (
    id uuid NOT NULL,
    db_password varchar(255) NOT NULL,
    db_username varchar(255) NOT NULL,
    db_port varchar(8) NOT NULL,
    db_name varchar(255) NOT NULL,
    db_host varchar(255) NOT NULL,
    db_provider varchar(255) NOT NULL,
    version int NOT NULL DEFAULT 0,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    CONSTRAINT pk_data_source_configurations PRIMARY KEY (id)
);
