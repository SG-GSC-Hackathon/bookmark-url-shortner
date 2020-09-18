drop schema if exists bookmarkurlshortner cascade;

create schema bookmarkurlshortner;

create table bookmarkurlshortner.card
(
    id           SERIAL PRIMARY KEY,
    title        varchar,
    description  varchar,
    tribe        varchar,
    team         varchar,
    component    varchar,
    created_by   varchar,
    updated_by   varchar,
    created_date timestamp,
    updated_date timestamp,
    active       boolean,
    url_id       int,
    icon_id      int
);

create table bookmarkurlshortner.group
(
    id           SERIAL PRIMARY KEY,
    name         varchar,
    description  varchar,
    tribe        varchar,
    team         varchar,
    component    varchar,
    created_by   varchar,
    updated_by   varchar,
    created_date timestamp,
    updated_date timestamp,
    active       boolean
);


create table bookmarkurlshortner.user
(
    id     SERIAL PRIMARY KEY,
    name   varchar,
    email  varchar,
    active boolean
);

create table bookmarkurlshortner.icon
(
    id      SERIAL PRIMARY KEY,
    name    varchar,
    type    varchar,
    card_id int references bookmarkurlshortner.card(id),
    file    oid
);


create table bookmarkurlshortner.user_in_group
(
    id         SERIAL PRIMARY KEY,
    email      varchar,
    group_id   int references bookmarkurlshortner.group(id),
    added_date date,
    added_by   varchar
);


create table bookmarkurlshortner.url
(
    id           SERIAL PRIMARY KEY,
    long_url     varchar,
    created_date date,
    card_id      int references bookmarkurlshortner.card(id),
    short_url    varchar,
    expires_date date
);


create table bookmarkurlshortner.card_in_group
(
    id         SERIAL PRIMARY KEY,
    card_id    int references bookmarkurlshortner.card(id),
    group_id   int references bookmarkurlshortner.group(id),
    added_date date,
    added_by   varchar
);

create table bookmarkurlshortner.suggestion_queue
(
    id           SERIAL PRIMARY KEY,
    card_id     int references bookmarkurlshortner.card(id),
    email        varchar,
    suggested_date date
);

