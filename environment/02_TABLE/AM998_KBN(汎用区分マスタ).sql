--���ʃ}�X�^(a) - �ėp�敪�}�X�^(am998_kbn)
create table am998_kbn(
     kbn_id varchar(50) not null,
     kbn_txt varchar(100) not null,
     kbn_name varchar(100) not null,
     biko varchar(100),
     enabled_flg boolean default true not null,
     disp_order smallint default 0 not null,
constraint am998_kbn_pk primary key (kbn_id, kbn_txt)
);

comment on table am998_kbn is '�ėp�敪�}�X�^';
comment on column am998_kbn.kbn_id is '�敪id';
comment on column am998_kbn.kbn_txt is '�敪�l';
comment on column am998_kbn.kbn_name is '�敪��';
comment on column am998_kbn.biko is '���l';
comment on column am998_kbn.enabled_flg is '�L���t���O';
comment on column am998_kbn.disp_order is '�\����';