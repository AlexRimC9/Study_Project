create table if not exists "user_role"(
        id_transaction BIGSERIAL not null,
        id_user BIGSERIAL not null,
        id_role BIGSERIAL not null,
        primary key (id_transaction)
)