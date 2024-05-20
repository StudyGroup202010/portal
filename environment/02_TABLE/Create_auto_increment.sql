#共通マスタ(a) - 社員マスタ(am005_employee)
alter table am005_employee change employee_id employee_id integer not null auto_increment comment '社員id';

#共通マスタ(a) - シーケンス(am997_sequence)
alter table am997_sequence change id id integer not null auto_increment comment 'シーケンス';
