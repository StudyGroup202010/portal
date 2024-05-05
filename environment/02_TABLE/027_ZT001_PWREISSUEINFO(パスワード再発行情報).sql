-- ポータルサイト(z) - パスワード再発行情報(zt001_pwreissueinfo)
create table zt001_pwreissueinfo(
     token varchar(128) not null,
     user_id varchar(50) not null,
     secret varchar(88) not null,
     expirydate timestamp not null,
constraint zt001_pwreissueinfo_pk primary key (token)
);

-- comment on table zt001_pwreissueinfo is 'パスワード再発行情報';
-- comment on column zt001_pwreissueinfo.token is 'トークン';
-- comment on column zt001_pwreissueinfo.user_id is 'ユーザid';
-- comment on column zt001_pwreissueinfo.secret is '秘密情報';
-- comment on column zt001_pwreissueinfo.expirydate is '認証情報有効期限';


-- foreign key constraints
alter table zt001_pwreissueinfo add constraint zt001_pwreissueinfo_fk1
      foreign key (user_id)
      references zm001_user (user_id);