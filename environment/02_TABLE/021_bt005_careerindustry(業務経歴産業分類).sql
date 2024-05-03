--スキル管理(b) - 業務経歴産業分類(bt005_careerindustry)
create table bt005_careerindustry(
     employee_id varchar(5) not null,
     certification_no smallint not null,
     industry_id varchar(10) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt005_careerindustry_pk primary key (employee_id, certification_no, industry_id)
);

comment on table bt005_careerindustry is '業務経歴産業分類';
comment on column bt005_careerindustry.employee_id is '社員id';
comment on column bt005_careerindustry.certification_no is '経歴番号';
comment on column bt005_careerindustry.industry_id is '産業分類id';
comment on column bt005_careerindustry.biko is '備考';
comment on column bt005_careerindustry.insert_user is '作成者';
comment on column bt005_careerindustry.insert_date is '作成日時';
comment on column bt005_careerindustry.update_user is '更新者';
comment on column bt005_careerindustry.update_date is '更新日時';

alter table bt005_careerindustry add constraint bt005_careerindustry_fk1
      foreign key (industry_id)
      references bm003_industry (industry_id);
alter table bt005_careerindustry add constraint bt005_careerindustry_fk2
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);