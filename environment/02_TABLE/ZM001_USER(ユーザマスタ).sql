--ポータルサイト(z) - ユーザマスタ(zm001_user)
create table zm001_user(
     user_id varchar(50) not null,
     user_due_date date default '2099-12-31' not null,
     password varchar(100) not null,
     pass_update date default '2099-12-31' not null,
     login_miss_times smallint default 0 not null,
     lock_flg boolean default false not null,
     employee_id varchar(5),
     enabled_flg boolean default true not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint zm001_user_pk primary key (user_id),
constraint zm001_user_sk1 unique (employee_id)
);

comment on table zm001_user is 'ユーザマスタ';
comment on column zm001_user.user_id is 'ユーザid';
comment on column zm001_user.user_due_date is 'ユーザ有効期限';
comment on column zm001_user.password is 'パスワード';
comment on column zm001_user.pass_update is 'パスワード有効期限';
comment on column zm001_user.login_miss_times is 'ログイン失敗回数';
comment on column zm001_user.lock_flg is 'ロック状態';
comment on column zm001_user.employee_id is '社員id';
comment on column zm001_user.enabled_flg is '有効フラグ';
comment on column zm001_user.insert_user is '作成者';
comment on column zm001_user.insert_date is '作成日時';
comment on column zm001_user.update_user is '更新者';
comment on column zm001_user.update_date is '更新日時';

alter table zm001_user add constraint zm001_user_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);
