CREATE TABLE transaction (
  id BIGINT NOT NULL,
   from_identifier VARCHAR(255),
   to_identifier VARCHAR(255),
   date date,
   remark VARCHAR(255),
   amount DECIMAL,
   CONSTRAINT pk_transaction PRIMARY KEY (id)
);