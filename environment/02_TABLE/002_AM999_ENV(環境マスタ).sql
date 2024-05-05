-- 共通マスタ(a) - 環境マスタ(am999_env)
create table am999_env(
     env_id varchar(50) not null,
     env_kbn varchar(20),
     env_num smallint default 0 not null,
     env_txt varchar(100) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am999_env_pk primary key (env_id)
);

-- comment on table am999_env is '環境マスタ';
-- comment on column am999_env.env_id is '環境ｉｄ';
-- comment on column am999_env.env_kbn is '環境区分';
-- comment on column am999_env.env_num is '環境連番';
-- comment on column am999_env.env_txt is '環境値';
-- comment on column am999_env.biko is '備考';
-- comment on column am999_env.insert_user is '作成者';
-- comment on column am999_env.insert_date is '作成日時';
-- comment on column am999_env.update_user is '更新者';
-- comment on column am999_env.update_date is '更新日時';
