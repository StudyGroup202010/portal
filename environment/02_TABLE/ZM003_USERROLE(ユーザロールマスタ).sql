--システム管理(z) - ユーザロールマスタ(zm003_userrole)
create table zm003_userrole(
     user_id varchar(50) not null,
     role_id varchar(50) not null,
constraint zm003_userrole_pk primary key (user_id, role_id)
);

comment on table zm003_userrole is 'ユーザロールマスタ';
comment on column zm003_userrole.user_id is 'ユーザid';
comment on column zm003_userrole.role_id is 'ロールｉｄ';

--foreign key constraints
alter table zm003_userrole add constraint zm003_userrole_fk1
      foreign key (role_id)
      references zm002_role (role_id);
alter table zm003_userrole add constraint zm003_userrole_fk2
      foreign key (user_id)
      references zm001_user (user_id);