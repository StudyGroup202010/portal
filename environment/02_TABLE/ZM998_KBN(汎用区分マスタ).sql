--VXeÇ(z) - Äpæª}X^(zm998_kbn)
create table zm998_kbn(
     kbn_id varchar(50) not null,
     kbn_txt varchar(100) not null,
     kbn_name varchar(100) not null,
     biko varchar(100),
     enabled_flg boolean default true not null,
     disp_order smallint default 0 not null,
constraint zm998_kbn_pk primary key (kbn_id, kbn_txt)
);

comment on table zm998_kbn is 'Äpæª}X^';
comment on column zm998_kbn.kbn_id is 'æªid';
comment on column zm998_kbn.kbn_txt is 'æªl';
comment on column zm998_kbn.kbn_name is 'æª¼';
comment on column zm998_kbn.biko is 'õl';
comment on column zm998_kbn.enabled_flg is 'LøtO';
comment on column zm998_kbn.disp_order is '\¦';
