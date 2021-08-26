create sequence if not exists hibernate_sequence start 1 increment 1;

create table if not exists messages (
    id int4 not null,
    username varchar(255),
    text varchar(255),
    primary key (id)
    );

insert into messages (id, username, text)
values (nextval('hibernate_sequence'), 'Cora_Iberkleid', 'Welcome to everyone!');
