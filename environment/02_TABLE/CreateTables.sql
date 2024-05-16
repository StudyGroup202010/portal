#���ʃ}�X�^(a) - ��Ѓ}�X�^(am001_company)
create table am001_company(
     company_cd varchar(5) not null comment '���cd',
     company_name varchar(50) not null comment '��Ж�',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am001_company_pk primary key (company_cd)
);

#���ʃ}�X�^(a) - �g�D�}�X�^(am002_organization)
create table am002_organization(
     organization_cd varchar(5) not null comment '�g�Dcd',
     organization_name varchar(50) not null comment '�g�D��',
     company_cd varchar(5) not null comment '���cd',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     end_yearmonth varchar(6) default '209912' not null comment '�ŏI�N��',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am002_organization_pk primary key (organization_cd)
);

#���ʃ}�X�^(a) - �g�D�K�w�}�X�^(am003_orghierarchy)
create table am003_orghierarchy(
     organization_cd varchar(5) not null comment '�g�Dcd',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     upperorganization_cd varchar(5) not null comment '��ʑg�Dcd',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am003_orghierarchy_pk primary key (organization_cd, start_yearmonth)
);

#���ʃ}�X�^(a) - �g�D��E�}�X�^(am004_orgposition)
create table am004_orgposition(
     organization_cd varchar(5) not null comment '�g�Dcd',
     position_cd varchar(5) not null comment '��Ecd',
     employee_id integer not null comment '�Ј�id',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am004_orgposition_pk primary key (organization_cd, position_cd, employee_id, start_yearmonth)
);

#���ʃ}�X�^(a) - �Ј��}�X�^(am005_employee)
create table am005_employee(
     employee_id integer not null comment '�Ј�id',
     employee_cd varchar(15) not null comment '�Ј�cd',
     employee_name1_last varchar(50) not null comment '�Ј��������i���j',
     employee_name1_first varchar(50) not null comment '�Ј��������i���j',
     employee_name2_last varchar(50) comment '�Ј����J�i�i���j',
     employee_name2_first varchar(50) comment '�Ј����J�i�i���j',
     gender_kbn varchar(1) not null comment '���ʋ敪',
     postcode varchar(7) default '0000000' not null comment '�X�֔ԍ�',
     prefcode varchar(2) default '00' not null comment '�s���{����cd',
     pref_name1 varchar(50) comment '�s���{�����P',
     pref_name2 varchar(50) comment '�s���{�����Q',
     citycode varchar(3) default '000' not null comment '�s�撬����cd',
     city_name1 varchar(100) comment '�s�撬�����P',
     city_name2 varchar(100) comment '�s�撬�����Q',
     streetaddress1 varchar(100) comment '�Z���P',
     streetaddress2 varchar(100) comment '�Z���Q',
     birthday date comment '���N����',
     nearest_station_code varchar(10) default '0000000000' not null comment '�Ŋ�w�R�[�h',
     nearest_station_name varchar(50) comment '�Ŋ�w��',
     mail varchar(50) not null comment '���[���A�h���X',
     joined_date date not null comment '���Г�',
     leave_date date comment '�ގГ�',
     employeeattribute_id varchar(2) not null comment '�Ј�����id',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am005_employee_pk primary key (employee_id),
constraint am005_employee_sk1 unique (mail),
constraint am005_employee_sk2 unique (employee_cd)
);

#���ʃ}�X�^(a) - �Ј������}�X�^(am006_employeebelongs)
create table am006_employeebelongs(
     employee_id integer not null comment '�Ј�id',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     organization_cd varchar(5) not null comment '�g�Dcd',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am006_employeebelongs_pk primary key (employee_id, start_yearmonth)
);

#���ʃ}�X�^(a) - ��E�}�X�^(am007_position)
create table am007_position(
     position_cd varchar(5) not null comment '��Ecd',
     position_name varchar(50) not null comment '��E��',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     end_yearmonth varchar(6) default '209912' not null comment '�ŏI�N��',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am007_position_pk primary key (position_cd)
);

#���ʃ}�X�^(a) - �Ј������}�X�^(am008_employeeattribute)
create table am008_employeeattribute(
     employeeattribute_id varchar(2) not null comment '�Ј�����id',
     employeeattribute_name varchar(50) not null comment '�Ј�������',
     biko varchar(50) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am008_employeeattribute_pk primary key (employeeattribute_id),
constraint am008_employeeattribute_sk1 unique (employeeattribute_name)
);

#���ʃ}�X�^(a) - �ėp�敪�}�X�^(am998_kbn)
create table am998_kbn(
     kbn_id varchar(50) not null comment '�敪id',
     kbn_txt varchar(100) not null comment '�敪�l',
     kbn_name varchar(100) not null comment '�敪��',
     biko varchar(100) comment '���l',
     enabled_flg boolean default true not null comment '�L���t���O',
     disp_order smallint default 0 not null comment '�\����',
constraint am998_kbn_pk primary key (kbn_id, kbn_txt)
);

#���ʃ}�X�^(a) - ���}�X�^(am999_env)
create table am999_env(
     env_id varchar(50) not null comment '������',
     env_kbn varchar(20) comment '���敪',
     env_num smallint default 0 not null comment '���A��',
     env_txt varchar(100) not null comment '���l',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint am999_env_pk primary key (env_id)
);

#�X�L���Ǘ�(b) - ���i�}�X�^(bm001_certification)
create table bm001_certification(
     certification_id varchar(3) not null comment '���iid',
     certification_name varchar(50) not null comment '���i��',
     organizer_name varchar(50) comment '��Òc�̖�',
     disp_order integer default 0 not null comment '�\����',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bm001_certification_pk primary key (certification_id),
constraint bm001_certification_sk1 unique (certification_name)
);

#�X�L���Ǘ�(b) - �Z�p�}�X�^(bm002_technology)
create table bm002_technology(
     technology_id varchar(3) not null comment '�Z�pid',
     technology_name varchar(50) not null comment '�Z�p��',
     technology_kbn varchar(2) not null comment '�Z�p�敪',
     disp_order integer default 0 not null comment '�\����',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bm002_technology_pk primary key (technology_id),
constraint bm002_technology_sk1 unique (technology_name)
);

#�X�L���Ǘ�(b) - �Y�ƕ��ރ}�X�^(bm003_industry)
create table bm003_industry(
     industry_id varchar(10) not null comment '�Y�ƕ���id',
     industry_name varchar(50) not null comment '�Y�ƕ��ޖ�',
     disp_order integer default 0 not null comment '�\����',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bm003_industry_pk primary key (industry_id),
constraint bm003_industry_sk1 unique (industry_name)
);

#�X�L���Ǘ�(b) - �H���}�X�^(bm004_process)
create table bm004_process(
     process_id varchar(2) not null comment '�H��id',
     process_name varchar(50) not null comment '�H����',
     process_name_short varchar(10) comment '�H������',
     disp_order integer default 0 not null comment '�\����',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bm004_process_pk primary key (process_id),
constraint bm004_process_sk1 unique (process_name)
);

#�X�L���Ǘ�(b) - �X�L�����(bt001_skill)
create table bt001_skill(
     employee_id integer not null comment '�Ј�id',
     final_education varchar(50) comment '�ŏI�w��',
     department varchar(50) comment '�w��',
     graduation_date varchar(6) comment '���ƔN��',
     notices varchar(100) comment '���L����',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt001_skill_pk primary key (employee_id)
);

#�X�L���Ǘ�(b) - �Ј����i(bt002_empcertification)
create table bt002_empcertification(
     employee_id integer not null comment '�Ј�id',
     certification_id varchar(3) not null comment '���iid',
     acquisition_date date comment '�擾��',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt002_empcertification_pk primary key (employee_id, certification_id)
);

#�X�L���Ǘ�(b) - �Ɩ��o��(bt003_career)
create table bt003_career(
     employee_id integer not null comment '�Ј�id',
     certification_no integer not null comment '�o��ԍ�',
     disp_order integer default 0 not null comment '�\����',
     start_yearmonth varchar(6) not null comment '�J�n�N��',
     end_yearmonth varchar(6) default '209912' comment '�I���N��',
     business_content varchar(300) comment '�Ɩ����e',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt003_career_pk primary key (employee_id, certification_no)
);

#�X�L���Ǘ�(b) - �Ɩ��o���Z�p(bt004_careertechnology)
create table bt004_careertechnology(
     employee_id integer not null comment '�Ј�id',
     certification_no integer not null comment '�o��ԍ�',
     technology_id varchar(3) not null comment '�Z�pid',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt004_careertechnology_pk primary key (employee_id, certification_no, technology_id)
);

#�X�L���Ǘ�(b) - �Ɩ��o���Y�ƕ���(bt005_careerindustry)
create table bt005_careerindustry(
     employee_id integer not null comment '�Ј�id',
     certification_no integer not null comment '�o��ԍ�',
     industry_id varchar(10) not null comment '�Y�ƕ���id',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt005_careerindustry_pk primary key (employee_id, certification_no, industry_id)
);

#�X�L���Ǘ�(b) - �Ɩ��o���H��(bt006_careerprocess)
create table bt006_careerprocess(
     employee_id integer not null comment '�Ј�id',
     certification_no integer not null comment '�o��ԍ�',
     process_id varchar(2) not null comment '�H��id',
     biko varchar(100) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint bt006_careerprocess_pk primary key (employee_id, certification_no, process_id)
);

#�|�[�^���T�C�g(z) - ���[�U�}�X�^(zm001_user)
create table zm001_user(
     user_id varchar(50) not null comment '���[�Uid',
     user_due_date date default '2099-12-31' not null comment '���[�U�L������',
     password varchar(100) not null comment '�p�X���[�h',
     pass_update date default '2099-12-31' not null comment '�p�X���[�h�L������',
     login_miss_times smallint default 0 not null comment '���O�C�����s��',
     lock_flg boolean default false not null comment '���b�N���',
     employee_id integer comment '�Ј�id',
     enabled_flg boolean default true not null comment '�L���t���O',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint zm001_user_pk primary key (user_id),
constraint zm001_user_sk1 unique (employee_id)
);

#�|�[�^���T�C�g(z) - ���[���}�X�^(zm002_role)
create table zm002_role(
     role_id varchar(50) not null comment '���[������',
     role_name varchar(50) comment '���[����',
     biko varchar(50) comment '���l',
     insert_user varchar(50) not null comment '�쐬��',
     insert_date timestamp not null comment '�쐬����',
     update_user varchar(50) comment '�X�V��',
     update_date timestamp comment '�X�V����',
constraint zm002_role_pk primary key (role_id),
constraint zm002_role_sk1 unique (role_name)
);

#�|�[�^���T�C�g(z) - ���[�U���[���}�X�^(zm003_userrole)
create table zm003_userrole(
     user_id varchar(50) not null comment '���[�Uid',
     role_id varchar(50) not null comment '���[������',
constraint zm003_userrole_pk primary key (user_id, role_id)
);

#�|�[�^���T�C�g(z) - ���[����(zm500_role_name_list)
create table zm500_role_name_list(
     role_name varchar(50) not null comment '���[����',
     enabled_flg boolean default true not null comment '�L���t���O',
constraint zm500_role_name_list_pk primary key (role_name)
);

#�|�[�^���T�C�g(z) - �p�X���[�h�Ĕ��s���(zt001_pwreissueinfo)
create table zt001_pwreissueinfo(
     token varchar(128) not null comment '�g�[�N��',
     user_id varchar(50) not null comment '���[�Uid',
     secret varchar(88) not null comment '�閧���',
     expirydate timestamp not null comment '�F�؏��L������',
constraint zt001_pwreissueinfo_pk primary key (token)
);
