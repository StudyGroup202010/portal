--共通マスタ(k) - 組織階層マスタ(km003_orghierarchy)
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

comment on table km003_orghierarchy is '組織階層マスタ';
comment on column km003_orghierarchy.organization_cd is '組織cd';
comment on column km003_orghierarchy.start_yearmonth is '開始年月';
comment on column km003_orghierarchy.upperorganization_cd is '上位組織cd';
comment on column km003_orghierarchy.insert_user is '作成者';
comment on column km003_orghierarchy.insert_date is '作成日時';
comment on column km003_orghierarchy.update_user is '更新者';
comment on column km003_orghierarchy.update_date is '更新日時';




alter table km003_orghierarchy add constraint km003_orghierarchy_fk1
      foreign key (organization_cd)
      references km002_organization (organization_cd);
alter table km003_orghierarchy add constraint km003_orghierarchy_fk2
      foreign key (upperorganization_cd)
      references km002_organization (organization_cd);
