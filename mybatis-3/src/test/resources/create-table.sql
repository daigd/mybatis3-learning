drop table `user` if exists;
create table `user`(
    id int generated by default as identity,
    name varchar(20),
    nick_name varchar(20),
    password varchar(20),
    phone varchar(11),
    primary key (id)
);
