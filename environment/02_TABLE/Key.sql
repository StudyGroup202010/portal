#foreign key constraints
alter table am002_organization add constraint am002_organization_fk1
      foreign key (company_cd)
      references am001_company (company_cd);
alter table am003_orghierarchy add constraint am003_orghierarchy_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am003_orghierarchy add constraint am003_orghierarchy_fk2
      foreign key (upperorganization_cd)
      references am002_organization (organization_cd);
alter table am004_orgposition add constraint am004_orgposition_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am004_orgposition add constraint am004_orgposition_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table am004_orgposition add constraint am004_orgposition_fk3
      foreign key (position_cd)
      references am007_position (position_cd);
alter table am005_employee add constraint am005_employee_fk1
      foreign key (employeeattribute_id)
      references am008_employeeattribute (employeeattribute_id);
alter table am006_employeebelongs add constraint am006_employeebelongs_fk1
      foreign key (organization_cd)
      references am002_organization (organization_cd);
alter table am006_employeebelongs add constraint am006_employeebelongs_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table bt001_skill add constraint bt001_skill_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table bt002_empcertification add constraint bt002_empcertification_fk1
      foreign key (certification_id)
      references bm001_certification (certification_id);
alter table bt002_empcertification add constraint bt002_empcertification_fk2
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table bt003_career add constraint bt003_career_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table bt004_careertechnology add constraint bt004_careertechnology_fk1
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);
alter table bt004_careertechnology add constraint bt004_careertechnology_fk2
      foreign key (technology_id)
      references bm002_technology (technology_id);
alter table bt005_careerindustry add constraint bt005_careerindustry_fk1
      foreign key (industry_id)
      references bm003_industry (industry_id);
alter table bt005_careerindustry add constraint bt005_careerindustry_fk2
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);
alter table bt006_careerprocess add constraint bt006_careerprocess_fk1
      foreign key (process_id)
      references bm004_process (process_id);
alter table bt006_careerprocess add constraint bt006_careerprocess_fk2
      foreign key (employee_id, certification_no)
      references bt003_career (employee_id, certification_no);
alter table zm001_user add constraint zm001_user_fk1
      foreign key (employee_id)
      references am005_employee (employee_id);
alter table zm002_role add constraint zm002_role_fk1
      foreign key (role_name)
      references zm500_role_name_list (role_name);
alter table zm003_userrole add constraint zm003_userrole_fk1
      foreign key (role_id)
      references zm002_role (role_id);
alter table zm003_userrole add constraint zm003_userrole_fk2
      foreign key (user_id)
      references zm001_user (user_id);
alter table zt001_pwreissueinfo add constraint zt001_pwreissueinfo_fk1
      foreign key (user_id)
      references zm001_user (user_id);