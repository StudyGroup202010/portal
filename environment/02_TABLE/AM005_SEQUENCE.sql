--社員マスタ
create sequence employee_id_seq
    increment by 1
    maxvalue 99999
    start with 1
    no cycle
;