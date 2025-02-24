create table company(
    id bigint not null auto_increment,
    name varchar(50),
    website varchar(100),
    description varchar(150),
    rating int,
    primary key (id)
);