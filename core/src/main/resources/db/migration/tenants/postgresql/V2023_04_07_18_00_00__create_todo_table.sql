CREATE TABLE todo (
   id UUID NOT NULL,
   title VARCHAR(255) NOT NULL,
   completed BOOLEAN NOT NULL,
   version BIGINT NOT NULL DEFAULT 0,
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   CONSTRAINT pk_todo PRIMARY KEY (id)
);
