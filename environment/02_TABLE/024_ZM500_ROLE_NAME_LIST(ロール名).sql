--ポータルサイト(z) - ロール名(zm500_role_name_list)
create table zm500_role_name_list(
     role_name varchar(50) not null,
     enabled_flg boolean default true not null,
constraint zm500_role_name_list_pk primary key (role_name)
);

comment on table zm500_role_name_list is 'ロール名';
comment on column zm500_role_name_list.role_name is 'ロール名';
comment on column zm500_role_name_list.enabled_flg is '有効フラグ';