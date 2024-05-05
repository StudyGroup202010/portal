-- スキル管理(b) - 技術マスタ(bm002_technology)
create table bm002_technology(
     technology_id varchar(3) not null,
     technology_name varchar(50) not null,
     technology_kbn varchar(2) not null,
     disp_order integer default 0 not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bm002_technology_pk primary key (technology_id),
constraint bm002_technology_sk1 unique (technology_name)
);

-- comment on table bm002_technology is '技術マスタ';
-- comment on column bm002_technology.technology_id is '技術id';
-- comment on column bm002_technology.technology_name is '技術名';
-- comment on column bm002_technology.technology_kbn is '技術区分';
-- comment on column bm002_technology.disp_order is '表示順';
-- comment on column bm002_technology.biko is '備考';
-- comment on column bm002_technology.insert_user is '作成者';
-- comment on column bm002_technology.insert_date is '作成日時';
-- comment on column bm002_technology.update_user is '更新者';
-- comment on column bm002_technology.update_date is '更新日時';