--共通マスタ(k) - 役職マスタ(km007_position)
create table km007_position(
     position_cd varchar(5) not null,
     position_name varchar(50) not null,
     start_yearmonth varchar(6) not null,
     end_yearmonth varchar(6) default 999912 not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km007_position_pk primary key (position_cd)
);

comment on table km007_position is '役職マスタ';
comment on column km007_position.position_cd is '役職cd';
comment on column km007_position.position_name is '役職名';
comment on column km007_position.start_yearmonth is '開始年月';
comment on column km007_position.end_yearmonth is '最終年月';
comment on column km007_position.insert_user is '作成者';
comment on column km007_position.insert_date is '作成日時';
comment on column km007_position.update_user is '更新者';
comment on column km007_position.update_date is '更新日時';