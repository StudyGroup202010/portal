--���ʃ}�X�^(k) - �g�D�K�w�}�X�^(km003_orghierarchy)
create table km003_orghierarchy(
     organization_cd varchar(10) not null,
     start_yearmonth varchar(6) not null,
     upperorganization_cd varchar(10) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km003_orghierarchy_pk primary key (organization_cd, start_yearmonth)
);

comment on table km003_orghierarchy is '�g�D�K�w�}�X�^';
comment on column km003_orghierarchy.organization_cd is '�g�Dcd';
comment on column km003_orghierarchy.start_yearmonth is '�J�n�N��';
comment on column km003_orghierarchy.upperorganization_cd is '��ʑg�Dcd';
comment on column km003_orghierarchy.insert_user is '�쐬��';
comment on column km003_orghierarchy.insert_date is '�쐬����';
comment on column km003_orghierarchy.update_user is '�X�V��';
comment on column km003_orghierarchy.update_date is '�X�V����';




alter table km003_orghierarchy add constraint km003_orghierarchy_fk1
      foreign key (organization_cd)
      references km002_organization (organization_cd);
alter table km003_orghierarchy add constraint km003_orghierarchy_fk2
      foreign key (upperorganization_cd)
      references km002_organization (organization_cd);
