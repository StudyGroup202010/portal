--共通マスタ(a) - 社員マスタ(am005_employee)
create table am005_employee(
     employee_id varchar(5) not null,
     employee_cd varchar(15) not null,
     employee_name1_last varchar(50) not null,
     employee_name1_first varchar(50) not null,
     employee_name2_last varchar(50),
     employee_name2_first varchar(50),
     gender_kbn varchar(1) not null,
     postcode varchar(7) default '0000000' not null,
     prefcode varchar(2) default '00' not null,
     pref_name1 varchar(50),
     pref_name2 varchar(50),
     citycode varchar(3) default '000' not null,
     city_name1 varchar(100),
     city_name2 varchar(100),
     streetaddress1 varchar(100),
     streetaddress2 varchar(100),
     birthday date,
     nearest_station_code varchar(10) default '0000000000' not null,
     nearest_station_name varchar(50),
     final_education varchar(50),
     department varchar(50),
     graduation_date varchar(6),
     mail varchar(50) not null,
     joined_date date not null,
     leave_date date default '2099/12/31',
     employeeattribute_id varchar(2) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am005_employee_pk primary key (employee_id),
constraint am005_employee_sk1 unique (mail),
constraint am005_employee_sk2 unique (employee_cd)
);

comment on table am005_employee is '社員マスタ';
comment on column am005_employee.employee_id is '社員id';
comment on column am005_employee.employee_cd is '社員cd';
comment on column am005_employee.employee_name1_last is '社員名漢字（性）';
comment on column am005_employee.employee_name1_first is '社員名漢字（名）';
comment on column am005_employee.employee_name2_last is '社員名カナ（性）';
comment on column am005_employee.employee_name2_first is '社員名カナ（名）';
comment on column am005_employee.gender_kbn is '性別区分';
comment on column am005_employee.postcode is '郵便番号';
comment on column am005_employee.prefcode is '都道府県名cd';
comment on column am005_employee.pref_name1 is '都道府県名１';
comment on column am005_employee.pref_name2 is '都道府県名２';
comment on column am005_employee.citycode is '市区町村名cd';
comment on column am005_employee.city_name1 is '市区町村名１';
comment on column am005_employee.city_name2 is '市区町村名２';
comment on column am005_employee.streetaddress1 is '住所１';
comment on column am005_employee.streetaddress2 is '住所２';
comment on column am005_employee.birthday is '生年月日';
comment on column am005_employee.nearest_station_code is '最寄駅コード';
comment on column am005_employee.nearest_station_name is '最寄駅名';
comment on column am005_employee.final_education is '最終学歴';
comment on column am005_employee.department is '学科';
comment on column am005_employee.graduation_date is '卒業年月';
comment on column am005_employee.mail is 'メールアドレス';
comment on column am005_employee.joined_date is '入社日';
comment on column am005_employee.leave_date is '退社日';
comment on column am005_employee.employeeattribute_id is '社員属性id';
comment on column am005_employee.biko is '備考';
comment on column am005_employee.insert_user is '作成者';
comment on column am005_employee.insert_date is '作成日時';
comment on column am005_employee.update_user is '更新者';
comment on column am005_employee.update_date is '更新日時';

alter table am005_employee add constraint am005_employee_fk1
      foreign key (employeeattribute_id)
      references am008_employeeattribute (employeeattribute_id);