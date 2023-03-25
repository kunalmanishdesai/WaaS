CREATE TABLE wallet_entity (
  id BIGINT NOT NULL,
   wallet_type VARCHAR(255),
   owner VARCHAR(255),
   user_id VARCHAR(255),
   company_id VARCHAR(255),
   balance DECIMAL,
   CONSTRAINT pk_walletentity PRIMARY KEY (id)
);

ALTER TABLE wallet_entity ADD CONSTRAINT uc_walletentity_userid UNIQUE (user_id);