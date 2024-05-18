#共通マスタ(a) - 社員マスタ(am005_employee)
ALTER TABLE am005_employee CHANGE employee_id employee_id integer not null AUTO_INCREMENT comment '社員id';


#スキル管理(b) - 業務経歴(bt003_career)
create table certification_no_seq (
    certification_no integer NOT NULL AUTO_INCREMENT PRIMARY KEY
) engine = InnoDB;



