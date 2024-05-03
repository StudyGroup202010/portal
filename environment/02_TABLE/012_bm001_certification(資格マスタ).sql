--スキル管理(b) - 資格マスタ(bm001_certification)
create table bm001_certification(
     certification_id varchar(3) not null,
     certification_name varchar(50) not null,
     organizer_name varchar(50),
     disp_order integer default 0 not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bm001_certification_pk primary key (certification_id),
constraint bm001_certification_sk1 unique (certification_name)
);

comment on table bm001_certification is '資格マスタ';
comment on column bm001_certification.certification_id is '資格id';
comment on column bm001_certification.certification_name is '資格名';
comment on column bm001_certification.organizer_name is '主催団体名';
comment on column bm001_certification.disp_order is '表示順';
comment on column bm001_certification.biko is '備考';
comment on column bm001_certification.insert_user is '作成者';
comment on column bm001_certification.insert_date is '作成日時';
comment on column bm001_certification.update_user is '更新者';
comment on column bm001_certification.update_date is '更新日時';