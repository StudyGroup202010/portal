#Foreign Key Constraints
ALTER TABLE AM002_ORGANIZATION ADD CONSTRAINT AM002_ORGANIZATION_FK1
      FOREIGN KEY (COMPANY_CD)
      REFERENCES AM001_COMPANY (COMPANY_CD);
ALTER TABLE AM003_ORGHIERARCHY ADD CONSTRAINT AM003_ORGHIERARCHY_FK1
      FOREIGN KEY (ORGANIZATION_CD)
      REFERENCES AM002_ORGANIZATION (ORGANIZATION_CD);
ALTER TABLE AM003_ORGHIERARCHY ADD CONSTRAINT AM003_ORGHIERARCHY_FK2
      FOREIGN KEY (UPPERORGANIZATION_CD)
      REFERENCES AM002_ORGANIZATION (ORGANIZATION_CD);
ALTER TABLE AM004_ORGPOSITION ADD CONSTRAINT AM004_ORGPOSITION_FK1
      FOREIGN KEY (ORGANIZATION_CD)
      REFERENCES AM002_ORGANIZATION (ORGANIZATION_CD);
ALTER TABLE AM004_ORGPOSITION ADD CONSTRAINT AM004_ORGPOSITION_FK2
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE AM004_ORGPOSITION ADD CONSTRAINT AM004_ORGPOSITION_FK3
      FOREIGN KEY (POSITION_CD)
      REFERENCES AM007_POSITION (POSITION_CD);
ALTER TABLE AM005_EMPLOYEE ADD CONSTRAINT AM005_EMPLOYEE_FK1
      FOREIGN KEY (EMPLOYEEATTRIBUTE_ID)
      REFERENCES AM008_EMPLOYEEATTRIBUTE (EMPLOYEEATTRIBUTE_ID);
ALTER TABLE AM006_EMPLOYEEBELONGS ADD CONSTRAINT AM006_EMPLOYEEBELONGS_FK1
      FOREIGN KEY (ORGANIZATION_CD)
      REFERENCES AM002_ORGANIZATION (ORGANIZATION_CD);
ALTER TABLE AM006_EMPLOYEEBELONGS ADD CONSTRAINT AM006_EMPLOYEEBELONGS_FK2
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE BT001_SKILL ADD CONSTRAINT BT001_SKILL_FK1
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE BT002_EMPCERTIFICATION ADD CONSTRAINT BT002_EMPCERTIFICATION_FK1
      FOREIGN KEY (CERTIFICATION_ID)
      REFERENCES BM001_CERTIFICATION (CERTIFICATION_ID);
ALTER TABLE BT002_EMPCERTIFICATION ADD CONSTRAINT BT002_EMPCERTIFICATION_FK2
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE BT003_CAREER ADD CONSTRAINT BT003_CAREER_FK1
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE BT004_CAREERTECHNOLOGY ADD CONSTRAINT BT004_CAREERTECHNOLOGY_FK1
      FOREIGN KEY (EMPLOYEE_ID, CERTIFICATION_NO)
      REFERENCES BT003_CAREER (EMPLOYEE_ID, CERTIFICATION_NO);
ALTER TABLE BT004_CAREERTECHNOLOGY ADD CONSTRAINT BT004_CAREERTECHNOLOGY_FK2
      FOREIGN KEY (TECHNOLOGY_ID)
      REFERENCES BM002_TECHNOLOGY (TECHNOLOGY_ID);
ALTER TABLE BT005_CAREERINDUSTRY ADD CONSTRAINT BT005_CAREERINDUSTRY_FK1
      FOREIGN KEY (INDUSTRY_ID)
      REFERENCES BM003_INDUSTRY (INDUSTRY_ID);
ALTER TABLE BT005_CAREERINDUSTRY ADD CONSTRAINT BT005_CAREERINDUSTRY_FK2
      FOREIGN KEY (EMPLOYEE_ID, CERTIFICATION_NO)
      REFERENCES BT003_CAREER (EMPLOYEE_ID, CERTIFICATION_NO);
ALTER TABLE BT006_CAREERPROCESS ADD CONSTRAINT BT006_CAREERPROCESS_FK1
      FOREIGN KEY (PROCESS_ID)
      REFERENCES BM004_PROCESS (PROCESS_ID);
ALTER TABLE BT006_CAREERPROCESS ADD CONSTRAINT BT006_CAREERPROCESS_FK2
      FOREIGN KEY (EMPLOYEE_ID, CERTIFICATION_NO)
      REFERENCES BT003_CAREER (EMPLOYEE_ID, CERTIFICATION_NO);
ALTER TABLE ZM001_USER ADD CONSTRAINT ZM001_USER_FK1
      FOREIGN KEY (EMPLOYEE_ID)
      REFERENCES AM005_EMPLOYEE (EMPLOYEE_ID);
ALTER TABLE ZM002_ROLE ADD CONSTRAINT ZM002_ROLE_FK1
      FOREIGN KEY (ROLE_NAME)
      REFERENCES ZM500_ROLE_NAME_LIST (ROLE_NAME);
ALTER TABLE ZM003_USERROLE ADD CONSTRAINT ZM003_USERROLE_FK1
      FOREIGN KEY (ROLE_ID)
      REFERENCES ZM002_ROLE (ROLE_ID);
ALTER TABLE ZM003_USERROLE ADD CONSTRAINT ZM003_USERROLE_FK2
      FOREIGN KEY (USER_ID)
      REFERENCES ZM001_USER (USER_ID);
ALTER TABLE ZT001_PWREISSUEINFO ADD CONSTRAINT ZT001_PWREISSUEINFO_FK1
      FOREIGN KEY (USER_ID)
      REFERENCES ZM001_USER (USER_ID);