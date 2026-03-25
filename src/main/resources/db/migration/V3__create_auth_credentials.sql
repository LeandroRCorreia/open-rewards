CREATE TABLE IF NOT EXISTS auth_credentials(
    id BIGSERIAL primary key,
    user_id BIGSERIAL not null,
    email varchar(255) not null,
    roles VARCHAR(50) NOT NULL DEFAULT 'USER',
    wallet_id BIGINT NOT NULL,
    password_hash varchar(255) not null,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE
);
