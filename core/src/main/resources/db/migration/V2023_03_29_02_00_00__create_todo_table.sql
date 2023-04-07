CREATE TABLE IF NOT EXISTS todo (
    id uuid NOT NULL,
    completed boolean NOT NULL,
    title varchar(255) NOT NULL,
    version integer NOT NULL DEFAULT 0,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);
