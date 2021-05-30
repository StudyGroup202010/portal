--スキル管理(b) - 業務経歴(bt003_career)
create table bt003_career(
     employee_id varchar(5) not null,
     certification_no smallint not null,
     start_yearmonth varchar(6),
     end_yearmonth varchar(6) default 999912,
     business_content varchar(300),
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt003_career_pk primary key (employee_id, certification_no)
);

comment on table bt003_career is '業務経歴';
comment on column bt003_career.employee_id is '社員id';
comment on column bt003_career.certification_no is '経歴番号';
comment on column bt003_career.start_yearmonth is '開始年月';
comment on column bt003_career.end_yearmonth is '終了年月';
comment on column bt003_career.business_content is '業務内容';
comment on column bt003_career.biko is '備考';
comment on column bt003_career.insert_user is '作成者';
comment on column bt003_career.insert_date is '作成日時';
comment on column bt003_career.update_user is '更新者';
comment on column bt003_career.update_date is '更新日時';

alter table bt003_career add constraint bt003_career_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);