create table if not exists "currencies"(
    name varchar not null,
    bid decimal not null,
    date_of_operation varchar not null,
    primary key (name)
)