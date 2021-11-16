--スキル管理(b) - スキル情報(bt001_skill)
create table bt001_skill(
     employee_id varchar(5) not null,
     final_education varchar(50),
     department varchar(50),
     graduation_date varchar(6),
     notices varchar(100),
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt001_skill_pk primary key (employee_id)
);

comment on table bt001_skill is 'スキル情報';
comment on column bt001_skill.employee_id is '社員id';
comment on column bt001_skill.final_education is '最終学歴';
comment on column bt001_skill.department is '学科';
comment on column bt001_skill.graduation_date is '卒業年月';
comment on column bt001_skill.notices is '特記事項';
comment on column bt001_skill.biko is '備考';
comment on column bt001_skill.insert_user is '作成者';
comment on column bt001_skill.insert_date is '作成日時';
comment on column bt001_skill.update_user is '更新者';
comment on column bt001_skill.update_date is '更新日時';

alter table bt001_skill add constraint bt001_skill_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);