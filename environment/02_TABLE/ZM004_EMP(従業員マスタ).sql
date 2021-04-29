--システム管理(z) - 従業員マスタ(zm004_emp)
create table zm004_emp(
     emp_id varchar(50) not null,
     emp_name varchar(50),
     mailaddress varchar(50),
     birthday date not null,
     marriage_flg boolean default false not null,
     user_id varchar(50),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint zm004_emp_pk primary key (emp_id)
);

comment on table zm004_emp is '従業員マスタ';
comment on column zm004_emp.emp_id is '従業員ｉｄ';
comment on column zm004_emp.emp_name is '従業員名';
comment on column zm004_emp.mailaddress is 'メールアドレス';
comment on column zm004_emp.birthday is '生年月日';
comment on column zm004_emp.marriage_flg is '結婚フラグ';
comment on column zm004_emp.user_id is 'ユーザid';
comment on column zm004_emp.insert_user is '作成者';
comment on column zm004_emp.insert_date is '作成日時';
comment on column zm004_emp.update_user is '更新者';
comment on column zm004_emp.update_date is '更新日時';


--foreign key constraints
alter table zm004_emp add constraint zm004_emp_fk1
      foreign key (user_id)
      references zm001_user (user_id);