--���ʃ}�X�^(k) - �g�D�}�X�^(am002_organization)
create table am002_organization(
     organization_cd varchar(10) not null,
     organization_name varchar(50) not null,
     company_cd varchar(5) not null,
     start_yearmonth varchar(6) not null,
     end_yearmonth varchar(6) default 999912 not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am002_organization_pk primary key (organization_cd)
);

comment on table am002_organization is '�g�D�}�X�^';
comment on column am002_organization.organization_cd is '�g�Dcd';
comment on column am002_organization.organization_name is '�g�D��';
comment on column am002_organization.company_cd is '���cd';
comment on column am002_organization.start_yearmonth is '�J�n�N��';
comment on column am002_organization.end_yearmonth is '�ŏI�N��';
comment on column am002_organization.insert_user is '�쐬��';
comment on column am002_organization.insert_date is '�쐬����';
comment on column am002_organization.update_user is '�X�V��';
comment on column am002_organization.update_date is '�X�V����';



--foreign key constraints
alter table am002_organization add constraint am002_organization_fk1
      foreign key (company_cd)
      references am001_company (company_cd);
