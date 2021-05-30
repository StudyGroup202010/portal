--スキル管理(b) - 業務経歴技術(bt004_careertechnology)
create table bt004_careertechnology(
     employee_id varchar(5) not null,
     certification_no smallint not null,
     technology_id varchar(3) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt004_careertechnology_pk primary key (employee_id, certification_no, technology_id)
);

comment on table bt004_careertechnology is '業務経歴技術';
comment on column bt004_careertechnology.employee_id is '社員id';
comment on column bt004_careertechnology.certification_no is '経歴番号';
comment on column bt004_careertechnology.technology_id is '技術id';
comment on column bt004_careertechnology.biko is '備考';
comment on column bt004_careertechnology.insert_user is '作成者';
comment on column bt004_careertechnology.insert_date is '作成日時';
comment on column bt004_careertechnology.update_user is '更新者';
comment on column bt004_careertechnology.update_date is '更新日時';

alter table bt004_careertechnology add constraint bt004_careertechnology_fk1
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);
alter table bt004_careertechnology add constraint bt004_careertechnology_fk2
      foreign key (technology_id)
      references bm002_technology (technology_id);