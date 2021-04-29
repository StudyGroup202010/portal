--�V�X�e���Ǘ�(z) - ���[���}�X�^(zm002_role)
create table zm002_role(
     role_id varchar(50) not null,
     role_name varchar(50),
     biko varchar(50),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint zm002_role_pk primary key (role_id),
constraint zm002_role_sk1 unique (role_name)
);

comment on table zm002_role is '���[���}�X�^';
comment on column zm002_role.role_id is '���[������';
comment on column zm002_role.role_name is '���[����';
comment on column zm002_role.biko is '���l';
comment on column zm002_role.insert_user is '�쐬��';
comment on column zm002_role.insert_date is '�쐬����';
comment on column zm002_role.update_user is '�X�V��';
comment on column zm002_role.update_date is '�X�V����';

alter table zm002_role add constraint zm002_role_fk1
      foreign key (role_name)
      references zm500_role_name_list (role_name);