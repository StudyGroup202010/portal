--スキル管理(b) - 工程マスタ(bm004_process)
create table bm004_process(
     process_id varchar(2) not null,
     process_name varchar(50) not null,
     process_name_short varchar(10),
     disp_order integer default 0 not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bm004_process_pk primary key (process_id),
constraint bm004_process_sk1 unique (process_name)
);

comment on table bm004_process is '工程マスタ';
comment on column bm004_process.process_id is '工程id';
comment on column bm004_process.process_name is '工程名';
comment on column bm004_process.process_name_short is '工程名略';
comment on column bm004_process.disp_order is '表示順';
comment on column bm004_process.biko is '備考';
comment on column bm004_process.insert_user is '作成者';
comment on column bm004_process.insert_date is '作成日時';
comment on column bm004_process.update_user is '更新者';
comment on column bm004_process.update_date is '更新日時';