create schema if not exists urls;
use urls;
create table if not exists urls
(
    id               int auto_increment,
    orig_url         varchar(256) unique not null,
    shortened_path   varchar(10) unique not null,
    created_date datetime    not null default now(),
    primary key(id)
);

create table if not exists history
(
    id           int auto_increment,
    url_id       int not null,
    operation    varchar(10),
    created_date datetime    not null default now(),
    primary key(id),
    foreign key (url_id)
        references urls(id)
        on delete cascade
);