-- 共通マスタ(a) - 会社マスタ(am001_company)
create table am001_company(
     company_cd varchar(5) not null,
     company_name varchar(50) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint am001_company_pk primary key (company_cd)
);

-- comment on table am001_company is '会社マスタ';
-- comment on column am001_company.company_cd is '会社cd';
-- comment on column am001_company.company_name is '会社名';
-- comment on column am001_company.biko is '備考';
-- comment on column am001_company.insert_user is '作成者';
-- comment on column am001_company.insert_date is '作成日時';
-- comment on column am001_company.update_user is '更新者';
-- comment on column am001_company.update_date is '更新日時';
