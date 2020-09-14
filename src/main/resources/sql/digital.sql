create schema bookmarkurlshortner;

create table bookmarkurlshortner.card
(
    id           bigint not null
        constraint card_pkey
            primary key,
    active       boolean,
    component    varchar,
    created_by   varchar,
    created_date timestamp,
    description  varchar,
    expire_date  timestamp,
    group_name   varchar,
    original_url varchar,
    short_url    varchar,
    team         varchar,
    title        varchar,
    tribe        varchar,
    updated_by   varchar,
    updated_date timestamp
);

create table bookmarkurlshortner.group
(
    id           bigint not null
        constraint group_pkey
            primary key,
    created_by   varchar,
    created_date timestamp,
    description  varchar,
    name         varchar,
    updated_by   varchar,
    updated_date timestamp,
    component    varchar,
    team         varchar,
    tribe        varchar,
    active       boolean,
    admin        varchar
);

create table bookmarkurlshortner.user
(
    id     integer not null
        constraint user_pkey
            primary key,
    email  varchar,
    active boolean not null
);

create table bookmarkurlshortner.icon
(
	id serial not null,
	name varchar,
	type varchar,
	card_id int,
	file oid
);


create unique index icon_id_uindex
	on bookmarkurlshortner.icon (id);

alter table bookmarkurlshortner.icon
	add constraint icon_pk
		primary key (id);

-----------------------------------------------

create table bookmarkurlshortner."user-in-group"
(
	id serial not null,
	email varchar,
	group_name varchar,
	added_date date,
	added_by varchar
);

create unique index "user-in-group_id_uindex"
	on bookmarkurlshortner."user-in-group" (id);

alter table bookmarkurlshortner."user-in-group"
	add constraint "user-in-group_pk"
		primary key (id);

--------------------------------------------

create table bookmarkurlshortner.url
(
	id serial not null,
	long_url varchar,
	created_date date,
	expires_date date
);

create unique index url_id_uindex
	on bookmarkurlshortner.url (id);

alter table bookmarkurlshortner.url
	add constraint url_pk
		primary key (id);

--------------------------------------

create table bookmarkurlshortner."card-in-group"
(
	id serial not null,
	card_id int,
	group_id int,
	added_date date,
	added_by varchar
);

create unique index "card-in-group_id_uindex"
	on bookmarkurlshortner."card-in-group" (id);

alter table bookmarkurlshortner."card-in-group"
	add constraint "card-in-group_pk"
		primary key (id);
