CREATE SEQUENCE IF NOT EXISTS transaction_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE transaction (
   id BIGINT NOT NULL,
   from_identifier VARCHAR(255),
   to_identifier VARCHAR(255),
   date_time DATETIME,
   remark VARCHAR(255),
   amount DECIMAL,
   CONSTRAINT pk_transaction PRIMARY KEY (id)
);