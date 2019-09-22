drop table if exists people;
drop table if exists peopledto;
create table people (id int primary key auto_increment, fullName varchar(255) not null, age int(3));
create table peopledto (id int primary key auto_increment, firstName varchar(255) not null, lastName varchar(255), bornDate varchar(255));
