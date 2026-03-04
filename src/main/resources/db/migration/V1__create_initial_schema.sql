CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE wallets (
     id BIGSERIAL PRIMARY KEY,
     user_id BIGINT NOT NULL UNIQUE,
     balance DECIMAL(19, 4) NOT NULL DEFAULT 0.0000,
     version BIGINT DEFAULT 0,
     updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT fk_wallet_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    wallet_id BIGINT NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    type VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaction_wallet FOREIGN KEY (wallet_id) REFERENCES wallets(id)
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_transactions_wallet_date ON transactions(wallet_id, created_at DESC);