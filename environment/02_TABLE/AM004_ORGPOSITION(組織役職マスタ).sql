--共通マスタ(k) - 組織役職マスタ(am004_orgposition)
create table am004_orgposition(
     organization_cd varchar(10) not null,
     position_cd varchar(5) not null,
     employee_id varchar(5) not null,
     start_yearmonth varchar(6) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am004_orgposition_pk primary key (organization_cd, position_cd, employee_id, start_yearmonth)
);

comment on table am004_orgposition is '組織役職マスタ';
comment on column am004_orgposition.organization_cd is '組織cd';
comment on column am004_orgposition.position_cd is '役職cd';
comment on column am004_orgposition.employee_id is '社員id';
comment on column am004_orgposition.start_yearmonth is '開始年月';
comment on column am004_orgposition.insert_user is '作成者';
comment on column am004_orgposition.insert_date is '作成日時';
comment on column am004_orgposition.update_user is '更新者';
comment on column am004_orgposition.update_date is '更新日時';

alter table am004_orgposition add constraint am004_orgposition_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am004_orgposition add constraint am004_orgposition_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table am004_orgposition add constraint am004_orgposition_fk3
      foreign key (position_cd)
      references am007_position (position_cd);