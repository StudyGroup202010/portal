--���ʃ}�X�^(a) - �g�D�K�w�}�X�^(am003_orghierarchy)
create table am003_orghierarchy(
     organization_cd varchar(5) not null,
     start_yearmonth varchar(6) not null,
     upperorganization_cd varchar(5) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am003_orghierarchy_pk primary key (organization_cd, start_yearmonth)
);

comment on table am003_orghierarchy is '�g�D�K�w�}�X�^';
comment on column am003_orghierarchy.organization_cd is '�g�Dcd';
comment on column am003_orghierarchy.start_yearmonth is '�J�n�N��';
comment on column am003_orghierarchy.upperorganization_cd is '��ʑg�Dcd';
comment on column am003_orghierarchy.biko is '���l';
comment on column am003_orghierarchy.insert_user is '�쐬��';
comment on column am003_orghierarchy.insert_date is '�쐬����';
comment on column am003_orghierarchy.update_user is '�X�V��';
comment on column am003_orghierarchy.update_date is '�X�V����';




alter table am003_orghierarchy add constraint am003_orghierarchy_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am003_orghierarchy add constraint am003_orghierarchy_fk2
      foreign key (upperorganization_cd)
      references am002_organization (organization_cd);
