--���ʃ}�X�^(k) - ��Ѓ}�X�^(km001_company)
create table km001_company(
     company_cd varchar(5) not null,
     company_name varchar(50) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km001_company_pk primary key (company_cd)
);

comment on table km001_company is '��Ѓ}�X�^';
comment on column km001_company.company_cd is '���cd';
comment on column km001_company.company_name is '��Ж�';
comment on column km001_company.insert_user is '�쐬��';
comment on column km001_company.insert_date is '�쐬����';
comment on column km001_company.update_user is '�X�V��';
comment on column km001_company.update_date is '�X�V����';