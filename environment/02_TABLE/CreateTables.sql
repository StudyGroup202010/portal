#共通マスタ(a) - 会社マスタ(am001_company)
create table am001_company(
     company_cd varchar(5) not null comment '会社cd',
     company_name varchar(50) not null comment '会社名',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am001_company_pk primary key (company_cd)
);

#共通マスタ(a) - 組織マスタ(am002_organization)
create table am002_organization(
     organization_cd varchar(5) not null comment '組織cd',
     organization_name varchar(50) not null comment '組織名',
     company_cd varchar(5) not null comment '会社cd',
     start_yearmonth varchar(6) not null comment '開始年月',
     end_yearmonth varchar(6) default '209912' not null comment '最終年月',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am002_organization_pk primary key (organization_cd)
);

#共通マスタ(a) - 組織階層マスタ(am003_orghierarchy)
create table am003_orghierarchy(
     organization_cd varchar(5) not null comment '組織cd',
     start_yearmonth varchar(6) not null comment '開始年月',
     upperorganization_cd varchar(5) not null comment '上位組織cd',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am003_orghierarchy_pk primary key (organization_cd, start_yearmonth)
);

#共通マスタ(a) - 組織役職マスタ(am004_orgposition)
create table am004_orgposition(
     organization_cd varchar(5) not null comment '組織cd',
     position_cd varchar(5) not null comment '役職cd',
     employee_id integer not null comment '社員id',
     start_yearmonth varchar(6) not null comment '開始年月',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am004_orgposition_pk primary key (organization_cd, position_cd, employee_id, start_yearmonth)
);

#共通マスタ(a) - 社員マスタ(am005_employee)
create table am005_employee(
     employee_id integer not null comment '社員id',
     employee_cd varchar(15) not null comment '社員cd',
     employee_name1_last varchar(50) not null comment '社員名漢字（姓）',
     employee_name1_first varchar(50) not null comment '社員名漢字（名）',
     employee_name2_last varchar(50) comment '社員名カナ（姓）',
     employee_name2_first varchar(50) comment '社員名カナ（名）',
     gender_kbn varchar(1) not null comment '性別区分',
     postcode varchar(7) default '0000000' not null comment '郵便番号',
     prefcode varchar(2) default '00' not null comment '都道府県名cd',
     pref_name1 varchar(50) comment '都道府県名１',
     pref_name2 varchar(50) comment '都道府県名２',
     citycode varchar(3) default '000' not null comment '市区町村名cd',
     city_name1 varchar(100) comment '市区町村名１',
     city_name2 varchar(100) comment '市区町村名２',
     streetaddress1 varchar(100) comment '住所１',
     streetaddress2 varchar(100) comment '住所２',
     birthday date comment '生年月日',
     nearest_station_code varchar(10) default '0000000000' not null comment '最寄駅コード',
     nearest_station_name varchar(50) comment '最寄駅名',
     mail varchar(50) not null comment 'メールアドレス',
     joined_date date not null comment '入社日',
     leave_date date comment '退社日',
     employeeattribute_id varchar(2) not null comment '社員属性id',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am005_employee_pk primary key (employee_id),
constraint am005_employee_sk1 unique (mail),
constraint am005_employee_sk2 unique (employee_cd)
);

#共通マスタ(a) - 社員所属マスタ(am006_employeebelongs)
create table am006_employeebelongs(
     employee_id integer not null comment '社員id',
     start_yearmonth varchar(6) not null comment '開始年月',
     organization_cd varchar(5) not null comment '組織cd',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am006_employeebelongs_pk primary key (employee_id, start_yearmonth)
);

#共通マスタ(a) - 役職マスタ(am007_position)
create table am007_position(
     position_cd varchar(5) not null comment '役職cd',
     position_name varchar(50) not null comment '役職名',
     start_yearmonth varchar(6) not null comment '開始年月',
     end_yearmonth varchar(6) default '209912' not null comment '最終年月',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am007_position_pk primary key (position_cd)
);

#共通マスタ(a) - 社員属性マスタ(am008_employeeattribute)
create table am008_employeeattribute(
     employeeattribute_id varchar(2) not null comment '社員属性id',
     employeeattribute_name varchar(50) not null comment '社員属性名',
     biko varchar(50) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am008_employeeattribute_pk primary key (employeeattribute_id),
constraint am008_employeeattribute_sk1 unique (employeeattribute_name)
);

#共通マスタ(a) - 汎用区分マスタ(am998_kbn)
create table am998_kbn(
     kbn_id varchar(50) not null comment '区分id',
     kbn_txt varchar(100) not null comment '区分値',
     kbn_name varchar(100) not null comment '区分名',
     biko varchar(100) comment '備考',
     enabled_flg boolean default true not null comment '有効フラグ',
     disp_order smallint default 0 not null comment '表示順',
constraint am998_kbn_pk primary key (kbn_id, kbn_txt)
);

#共通マスタ(a) - 環境マスタ(am999_env)
create table am999_env(
     env_id varchar(50) not null comment '環境ｉｄ',
     env_kbn varchar(20) comment '環境区分',
     env_num smallint default 0 not null comment '環境連番',
     env_txt varchar(100) not null comment '環境値',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint am999_env_pk primary key (env_id)
);

#スキル管理(b) - 資格マスタ(bm001_certification)
create table bm001_certification(
     certification_id varchar(3) not null comment '資格id',
     certification_name varchar(50) not null comment '資格名',
     organizer_name varchar(50) comment '主催団体名',
     disp_order integer default 0 not null comment '表示順',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bm001_certification_pk primary key (certification_id),
constraint bm001_certification_sk1 unique (certification_name)
);

#スキル管理(b) - 技術マスタ(bm002_technology)
create table bm002_technology(
     technology_id varchar(3) not null comment '技術id',
     technology_name varchar(50) not null comment '技術名',
     technology_kbn varchar(2) not null comment '技術区分',
     disp_order integer default 0 not null comment '表示順',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bm002_technology_pk primary key (technology_id),
constraint bm002_technology_sk1 unique (technology_name)
);

#スキル管理(b) - 産業分類マスタ(bm003_industry)
create table bm003_industry(
     industry_id varchar(10) not null comment '産業分類id',
     industry_name varchar(50) not null comment '産業分類名',
     disp_order integer default 0 not null comment '表示順',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bm003_industry_pk primary key (industry_id),
constraint bm003_industry_sk1 unique (industry_name)
);

#スキル管理(b) - 工程マスタ(bm004_process)
create table bm004_process(
     process_id varchar(2) not null comment '工程id',
     process_name varchar(50) not null comment '工程名',
     process_name_short varchar(10) comment '工程名略',
     disp_order integer default 0 not null comment '表示順',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bm004_process_pk primary key (process_id),
constraint bm004_process_sk1 unique (process_name)
);

#スキル管理(b) - スキル情報(bt001_skill)
create table bt001_skill(
     employee_id integer not null comment '社員id',
     final_education varchar(50) comment '最終学歴',
     department varchar(50) comment '学科',
     graduation_date varchar(6) comment '卒業年月',
     notices varchar(100) comment '特記事項',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt001_skill_pk primary key (employee_id)
);

#スキル管理(b) - 社員資格(bt002_empcertification)
create table bt002_empcertification(
     employee_id integer not null comment '社員id',
     certification_id varchar(3) not null comment '資格id',
     acquisition_date date comment '取得日',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt002_empcertification_pk primary key (employee_id, certification_id)
);

#スキル管理(b) - 業務経歴(bt003_career)
create table bt003_career(
     employee_id integer not null comment '社員id',
     certification_no integer not null comment '経歴番号',
     disp_order integer default 0 not null comment '表示順',
     start_yearmonth varchar(6) not null comment '開始年月',
     end_yearmonth varchar(6) default '209912' comment '終了年月',
     business_content varchar(300) comment '業務内容',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt003_career_pk primary key (employee_id, certification_no)
);

#スキル管理(b) - 業務経歴技術(bt004_careertechnology)
create table bt004_careertechnology(
     employee_id integer not null comment '社員id',
     certification_no integer not null comment '経歴番号',
     technology_id varchar(3) not null comment '技術id',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt004_careertechnology_pk primary key (employee_id, certification_no, technology_id)
);

#スキル管理(b) - 業務経歴産業分類(bt005_careerindustry)
create table bt005_careerindustry(
     employee_id integer not null comment '社員id',
     certification_no integer not null comment '経歴番号',
     industry_id varchar(10) not null comment '産業分類id',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt005_careerindustry_pk primary key (employee_id, certification_no, industry_id)
);

#スキル管理(b) - 業務経歴工程(bt006_careerprocess)
create table bt006_careerprocess(
     employee_id integer not null comment '社員id',
     certification_no integer not null comment '経歴番号',
     process_id varchar(2) not null comment '工程id',
     biko varchar(100) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint bt006_careerprocess_pk primary key (employee_id, certification_no, process_id)
);

#ポータルサイト(z) - ユーザマスタ(zm001_user)
create table zm001_user(
     user_id varchar(50) not null comment 'ユーザid',
     user_due_date date default '2099-12-31' not null comment 'ユーザ有効期限',
     password varchar(100) not null comment 'パスワード',
     pass_update date default '2099-12-31' not null comment 'パスワード有効期限',
     login_miss_times smallint default 0 not null comment 'ログイン失敗回数',
     lock_flg boolean default false not null comment 'ロック状態',
     employee_id integer comment '社員id',
     enabled_flg boolean default true not null comment '有効フラグ',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint zm001_user_pk primary key (user_id),
constraint zm001_user_sk1 unique (employee_id)
);

#ポータルサイト(z) - ロールマスタ(zm002_role)
create table zm002_role(
     role_id varchar(50) not null comment 'ロールｉｄ',
     role_name varchar(50) comment 'ロール名',
     biko varchar(50) comment '備考',
     insert_user varchar(50) not null comment '作成者',
     insert_date timestamp not null comment '作成日時',
     update_user varchar(50) comment '更新者',
     update_date timestamp comment '更新日時',
constraint zm002_role_pk primary key (role_id),
constraint zm002_role_sk1 unique (role_name)
);

#ポータルサイト(z) - ユーザロールマスタ(zm003_userrole)
create table zm003_userrole(
     user_id varchar(50) not null comment 'ユーザid',
     role_id varchar(50) not null comment 'ロールｉｄ',
constraint zm003_userrole_pk primary key (user_id, role_id)
);

#ポータルサイト(z) - ロール名(zm500_role_name_list)
create table zm500_role_name_list(
     role_name varchar(50) not null comment 'ロール名',
     enabled_flg boolean default true not null comment '有効フラグ',
constraint zm500_role_name_list_pk primary key (role_name)
);

#ポータルサイト(z) - パスワード再発行情報(zt001_pwreissueinfo)
create table zt001_pwreissueinfo(
     token varchar(128) not null comment 'トークン',
     user_id varchar(50) not null comment 'ユーザid',
     secret varchar(88) not null comment '秘密情報',
     expirydate timestamp not null comment '認証情報有効期限',
constraint zt001_pwreissueinfo_pk primary key (token)
);
