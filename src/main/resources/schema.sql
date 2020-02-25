drop table if exists konamon;

create table konamon (id int primary key auto_increment, name varchar, description varchar, updated_at datetime, created_at datetime);

drop table if exists test;
create table test (id int, name varchar);
