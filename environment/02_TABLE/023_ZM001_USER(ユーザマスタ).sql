-- �|�[�^���T�C�g(z) - ���[�U�}�X�^(zm001_user)
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

-- comment on table zm001_user is '���[�U�}�X�^';
-- comment on column zm001_user.user_id is '���[�Uid';
-- comment on column zm001_user.user_due_date is '���[�U�L������';
-- comment on column zm001_user.password is '�p�X���[�h';
-- comment on column zm001_user.pass_update is '�p�X���[�h�L������';
-- comment on column zm001_user.login_miss_times is '���O�C�����s��';
-- comment on column zm001_user.lock_flg is '���b�N���';
-- comment on column zm001_user.employee_id is '�Ј�id';
-- comment on column zm001_user.enabled_flg is '�L���t���O';
-- comment on column zm001_user.insert_user is '�쐬��';
-- comment on column zm001_user.insert_date is '�쐬����';
-- comment on column zm001_user.update_user is '�X�V��';
-- comment on column zm001_user.update_date is '�X�V����';

alter table zm001_user add constraint zm001_user_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);