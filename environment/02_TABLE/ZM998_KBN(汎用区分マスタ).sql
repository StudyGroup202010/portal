--システム管理(z) - 汎用区分マスタ(zm998_kbn)
create table zm998_kbn(
     kbn_id varchar(50) not null,
     kbn_txt varchar(100) not null,
     kbn_name varchar(100) not null,
     biko varchar(100),
     enabled_flg boolean default true not null,
     disp_order smallint default 0 not null,
constraint zm998_kbn_pk primary key (kbn_id, kbn_txt)
);

comment on table zm998_kbn is '汎用区分マスタ';
comment on column zm998_kbn.kbn_id is '区分id';
comment on column zm998_kbn.kbn_txt is '区分値';
comment on column zm998_kbn.kbn_name is '区分名';
comment on column zm998_kbn.biko is '備考';
comment on column zm998_kbn.enabled_flg is '有効フラグ';
comment on column zm998_kbn.disp_order is '表示順';
