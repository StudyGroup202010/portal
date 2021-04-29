--共通マスタ(k) - 組織マスタ(km002_organization)
create table km002_organization(
     organization_cd varchar(10) not null,
     organization_name varchar(50) not null,
     company_cd varchar(5) not null,
     start_yearmonth varchar(6) not null,
     end_yearmonth varchar(6) default 999912 not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km002_organization_pk primary key (organization_cd)
);

comment on table km002_organization is '組織マスタ';
comment on column km002_organization.organization_cd is '組織cd';
comment on column km002_organization.organization_name is '組織名';
comment on column km002_organization.company_cd is '会社cd';
comment on column km002_organization.start_yearmonth is '開始年月';
comment on column km002_organization.end_yearmonth is '最終年月';
comment on column km002_organization.insert_user is '作成者';
comment on column km002_organization.insert_date is '作成日時';
comment on column km002_organization.update_user is '更新者';
comment on column km002_organization.update_date is '更新日時';



--foreign key constraints
alter table km002_organization add constraint km002_organization_fk1
      foreign key (company_cd)
      references km001_company (company_cd);
