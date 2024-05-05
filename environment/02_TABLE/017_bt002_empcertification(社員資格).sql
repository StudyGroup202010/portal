-- スキル管理(b) - 社員資格(bt002_empcertification)
create table bt002_empcertification(
     employee_id varchar(5) not null,
     certification_id varchar(3) not null,
     acquisition_date date,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bt002_empcertification_pk primary key (employee_id, certification_id)
);

-- comment on table bt002_empcertification is '社員資格';
-- comment on column bt002_empcertification.employee_id is '社員id';
-- comment on column bt002_empcertification.certification_id is '資格id';
-- comment on column bt002_empcertification.acquisition_date is '取得日';
-- comment on column bt002_empcertification.biko is '備考';
-- comment on column bt002_empcertification.insert_user is '作成者';
-- comment on column bt002_empcertification.insert_date is '作成日時';
-- comment on column bt002_empcertification.update_user is '更新者';
-- comment on column bt002_empcertification.update_date is '更新日時';

alter table bt002_empcertification add constraint bt002_empcertification_fk1
      foreign key (certification_id)
      references bm001_certification (certification_id);
alter table bt002_empcertification add constraint bt002_empcertification_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);