--共通マスタ(k) - 会社マスタ(km001_company)
create table km001_company(
     company_cd varchar(5) not null,
     company_name varchar(50) not null,
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint km001_company_pk primary key (company_cd)
);

comment on table km001_company is '会社マスタ';
comment on column km001_company.company_cd is '会社cd';
comment on column km001_company.company_name is '会社名';
comment on column km001_company.insert_user is '作成者';
comment on column km001_company.insert_date is '作成日時';
comment on column km001_company.update_user is '更新者';
comment on column km001_company.update_date is '更新日時';