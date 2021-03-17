--�V�X�e���Ǘ�(z) - �]�ƈ��}�X�^(zm004_emp)
create table zm004_emp(
     emp_id varchar(50) not null,
     emp_name varchar(50),
     mailaddress varchar(50),
     birthday date not null,
     marriage_flg boolean default false not null,
     user_id varchar(50),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint zm004_emp_pk primary key (emp_id)
);

comment on table zm004_emp is '�]�ƈ��}�X�^';
comment on column zm004_emp.emp_id is '�]�ƈ�����';
comment on column zm004_emp.emp_name is '�]�ƈ���';
comment on column zm004_emp.mailaddress is '���[���A�h���X';
comment on column zm004_emp.birthday is '���N����';
comment on column zm004_emp.marriage_flg is '�����t���O';
comment on column zm004_emp.user_id is '���[�Uid';
comment on column zm004_emp.insert_user is '�쐬��';
comment on column zm004_emp.insert_date is '�쐬����';
comment on column zm004_emp.update_user is '�X�V��';
comment on column zm004_emp.update_date is '�X�V����';


--foreign key constraints
alter table zm004_emp add constraint zm004_emp_fk1
      foreign key (user_id)
      references zm001_user (user_id);