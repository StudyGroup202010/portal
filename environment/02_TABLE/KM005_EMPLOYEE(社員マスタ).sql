--共通マスタ(k) - 社員マスタ(km005_employee)
create table km005_employee(
     employee_id varchar(5) not null,
     employee_name varchar(50) not null,
     employee_cd varchar(15) not null,
     mail varchar(50) not null,
     joined_date date not null,
     leave_date date default '9999/12/31' not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km005_employee_pk primary key (employee_id),
constraint km005_employee_sk1 unique (mail)
);

comment on table km005_employee is '社員マスタ';
comment on column km005_employee.employee_id is '社員id';
comment on column km005_employee.employee_name is '社員名';
comment on column km005_employee.employee_cd is '社員cd';
comment on column km005_employee.mail is 'メールアドレス';
comment on column km005_employee.joined_date is '入社日';
comment on column km005_employee.leave_date is '退社日';
comment on column km005_employee.insert_user is '作成者';
comment on column km005_employee.insert_date is '作成日時';
comment on column km005_employee.update_user is '更新者';
comment on column km005_employee.update_date is '更新日時';