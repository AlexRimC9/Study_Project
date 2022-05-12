create table if not exists "password"(
    username varchar not null,
    password varchar not null,
    used boolean null,
    primary key (password)
)