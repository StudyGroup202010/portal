--�V�X�e���Ǘ�(z) - ���}�X�^(zm999_env)
create table zm999_env(
     env_id varchar(50) not null,
     env_kbn varchar(20),
     env_num smallint default 0 not null,
     env_txt varchar(100) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint zm999_env_pk primary key (env_id)
);

comment on table zm999_env is '���}�X�^';
comment on column zm999_env.env_id is '������';
comment on column zm999_env.env_kbn is '���敪';
comment on column zm999_env.env_num is '���A��';
comment on column zm999_env.env_txt is '���l';
comment on column zm999_env.biko is '���l';
comment on column zm999_env.insert_user is '�쐬��';
comment on column zm999_env.insert_date is '�쐬����';
comment on column zm999_env.update_user is '�X�V��';
comment on column zm999_env.update_date is '�X�V����';