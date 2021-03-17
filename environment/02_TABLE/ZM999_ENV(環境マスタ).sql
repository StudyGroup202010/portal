--システム管理(z) - 環境マスタ(zm999_env)
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

comment on table zm999_env is '環境マスタ';
comment on column zm999_env.env_id is '環境ｉｄ';
comment on column zm999_env.env_kbn is '環境区分';
comment on column zm999_env.env_num is '環境連番';
comment on column zm999_env.env_txt is '環境値';
comment on column zm999_env.biko is '備考';
comment on column zm999_env.insert_user is '作成者';
comment on column zm999_env.insert_date is '作成日時';
comment on column zm999_env.update_user is '更新者';
comment on column zm999_env.update_date is '更新日時';