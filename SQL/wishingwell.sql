create database wishing_well;
use wishing_well;

create table users (
                              user_id int auto_increment,
                              email varchar(255) unique not null,
                              password varchar(255) not null,
                              name varchar(255),
                              primary key (user_id)
);

create table wishing_list
(
    wish_id   int auto_increment,
    wish_description varchar(255) not null,
    email     varchar(255) unique not null,
    user_id varchar(255),
    primary key (wish_id),
    foreign key (user_id) references users(user_id)
);

