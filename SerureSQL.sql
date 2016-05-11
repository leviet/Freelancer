--Create table role
create table role (
    role_id char(6) not null,
    name char(20),
    code char(10),
    PRIMARY KEY(role_id)
);
--Create table user_role
create table user_role (
    user_role_id char(6) not null,
    user_id char(6),
    role_id char(6),
    PRIMARY KEY(user_role_id)
);
--Create data role
insert into role (role_id, name, code)
values ('1', 'Administrator', 'ADMIN');
insert into role (role_id, name, code)
values ('2', 'Employee', 'EMPLOYEE');
