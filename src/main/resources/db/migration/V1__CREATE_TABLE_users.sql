create table if not exists "user_list"(
    id_user BIGSERIAL not null,
    username VARCHAR not null,
    first_name VARCHAR default null,
    second_name VARCHAR default null,
    password VARCHAR default null,
    age varchar default null,
    image_url VARCHAR default null,
    primary key (id_user)
);