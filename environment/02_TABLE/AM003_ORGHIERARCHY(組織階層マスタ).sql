--共通マスタ(a) - 組織階層マスタ(am003_orghierarchy)
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

comment on table am003_orghierarchy is '組織階層マスタ';
comment on column am003_orghierarchy.organization_cd is '組織cd';
comment on column am003_orghierarchy.start_yearmonth is '開始年月';
comment on column am003_orghierarchy.upperorganization_cd is '上位組織cd';
comment on column am003_orghierarchy.biko is '備考';
comment on column am003_orghierarchy.insert_user is '作成者';
comment on column am003_orghierarchy.insert_date is '作成日時';
comment on column am003_orghierarchy.update_user is '更新者';
comment on column am003_orghierarchy.update_date is '更新日時';




alter table am003_orghierarchy add constraint am003_orghierarchy_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am003_orghierarchy add constraint am003_orghierarchy_fk2
      foreign key (upperorganization_cd)
      references am002_organization (organization_cd);
