create table department (id UUID not null, color varchar(255), name varchar(255), primary key (id));
create table employee (id UUID not null, first_name varchar(255), last_name varchar(255), email varchar(300), dept_id UUID, primary key (id))