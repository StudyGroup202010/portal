--共通マスタ(k) - 社員所属マスタ(am006_employeebelongs)
create table am006_employeebelongs(
     employee_id varchar(5) not null,
     start_yearmonth varchar(6) not null,
     organization_cd varchar(10) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am006_employeebelongs_pk primary key (employee_id, start_yearmonth)
);

comment on table am006_employeebelongs is '社員所属マスタ';
comment on column am006_employeebelongs.employee_id is '社員id';
comment on column am006_employeebelongs.start_yearmonth is '開始年月';
comment on column am006_employeebelongs.organization_cd is '組織cd';
comment on column am006_employeebelongs.insert_user is '作成者';
comment on column am006_employeebelongs.insert_date is '作成日時';
comment on column am006_employeebelongs.update_user is '更新者';
comment on column am006_employeebelongs.update_date is '更新日時';


alter table am006_employeebelongs add constraint am006_employeebelongs_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am006_employeebelongs add constraint am006_employeebelongs_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);