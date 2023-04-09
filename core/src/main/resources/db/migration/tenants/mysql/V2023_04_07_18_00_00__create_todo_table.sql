CREATE TABLE todo (
   id char(36) NOT NULL,
   title VARCHAR(255) NULL,
   completed BIT(1) NOT NULL,
   version BIGINT NULL DEFAULT 0,
   created_at datetime NULL,
   updated_at datetime NULL,
   CONSTRAINT pk_todo PRIMARY KEY (id)
);