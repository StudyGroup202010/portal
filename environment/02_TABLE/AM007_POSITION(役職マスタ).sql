--���ʃ}�X�^(k) - ��E�}�X�^(am007_position)
create table am007_position(
     position_cd varchar(5) not null,
     position_name varchar(50) not null,
     start_yearmonth varchar(6) not null,
     end_yearmonth varchar(6) default 999912 not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am007_position_pk primary key (position_cd)
);

comment on table am007_position is '��E�}�X�^';
comment on column am007_position.position_cd is '��Ecd';
comment on column am007_position.position_name is '��E��';
comment on column am007_position.start_yearmonth is '�J�n�N��';
comment on column am007_position.end_yearmonth is '�ŏI�N��';
comment on column am007_position.insert_user is '�쐬��';
comment on column am007_position.insert_date is '�쐬����';
comment on column am007_position.update_user is '�X�V��';
comment on column am007_position.update_date is '�X�V����';
