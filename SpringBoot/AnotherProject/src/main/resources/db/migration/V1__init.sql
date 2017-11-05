create table categories (id  bigserial not null, name varchar(255), primary key (id));
create table users (id  bigserial not null, born_date date, name varchar(255), category_id serial not null, primary key (id));
alter table users add constraint FKarx2fw0li5fodwh99f3eeqb5 foreign key (category_id) references categories;