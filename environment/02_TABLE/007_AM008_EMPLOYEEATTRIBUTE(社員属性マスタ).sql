-- 共通マスタ(a) - 社員属性マスタ(am008_employeeattribute)
create table am008_employeeattribute(
     employeeattribute_id varchar(2) not null,
     employeeattribute_name varchar(50) not null,
     biko varchar(50),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am008_employeeattribute_pk primary key (employeeattribute_id),
constraint am008_employeeattribute_sk1 unique (employeeattribute_name)
);

-- comment on table am008_employeeattribute is '社員属性マスタ';
-- comment on column am008_employeeattribute.employeeattribute_id is '社員属性id';
-- comment on column am008_employeeattribute.employeeattribute_name is '社員属性名';
-- comment on column am008_employeeattribute.biko is '備考';
-- comment on column am008_employeeattribute.insert_user is '作成者';
-- comment on column am008_employeeattribute.insert_date is '作成日時';
-- comment on column am008_employeeattribute.update_user is '更新者';
-- comment on column am008_employeeattribute.update_date is '更新日時';