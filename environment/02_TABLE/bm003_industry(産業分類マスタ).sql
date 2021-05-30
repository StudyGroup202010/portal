--スキル管理(b) - 産業分類マスタ(bm003_industry)
create table bm003_industry(
     industry_id varchar(10) not null,
     industry_name varchar(50) not null,
     biko varchar(100),
     insert_user varchar(50) not null,
     insert_date timestamp not null,
     update_user varchar(50),
     update_date timestamp,
constraint bm003_industry_pk primary key (industry_id)
);

comment on table bm003_industry is '産業分類マスタ';
comment on column bm003_industry.industry_id is '産業分類id';
comment on column bm003_industry.industry_name is '産業分類名';
comment on column bm003_industry.biko is '備考';
comment on column bm003_industry.insert_user is '作成者';
comment on column bm003_industry.insert_date is '作成日時';
comment on column bm003_industry.update_user is '更新者';
comment on column bm003_industry.update_date is '更新日時';