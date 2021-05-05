--共通マスタ(k) - 組織マスタ(am002_organization)
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

comment on table am002_organization is '組織マスタ';
comment on column am002_organization.organization_cd is '組織cd';
comment on column am002_organization.organization_name is '組織名';
comment on column am002_organization.company_cd is '会社cd';
comment on column am002_organization.start_yearmonth is '開始年月';
comment on column am002_organization.end_yearmonth is '最終年月';
comment on column am002_organization.insert_user is '作成者';
comment on column am002_organization.insert_date is '作成日時';
comment on column am002_organization.update_user is '更新者';
comment on column am002_organization.update_date is '更新日時';



--foreign key constraints
alter table am002_organization add constraint am002_organization_fk1
      foreign key (company_cd)
      references am001_company (company_cd);
