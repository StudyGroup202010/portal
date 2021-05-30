--スキル管理(b) - 業務経歴工程(bt006_careerprocess)
create table bt006_careerprocess(
     employee_id varchar(5) not null,
     certification_no smallint not null,
     process_id varchar(2) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt006_careerprocess_pk primary key (employee_id, certification_no, process_id)
);

comment on table bt006_careerprocess is '業務経歴工程';
comment on column bt006_careerprocess.employee_id is '社員id';
comment on column bt006_careerprocess.certification_no is '経歴番号';
comment on column bt006_careerprocess.process_id is '工程id';
comment on column bt006_careerprocess.biko is '備考';
comment on column bt006_careerprocess.insert_user is '作成者';
comment on column bt006_careerprocess.insert_date is '作成日時';
comment on column bt006_careerprocess.update_user is '更新者';
comment on column bt006_careerprocess.update_date is '更新日時';

alter table bt006_careerprocess add constraint bt006_careerprocess_fk1
      foreign key (process_id)
      references bm004_process (process_id);
alter table bt006_careerprocess add constraint bt006_careerprocess_fk2
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);