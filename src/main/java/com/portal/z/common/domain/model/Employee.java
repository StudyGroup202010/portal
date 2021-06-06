package com.portal.z.common.domain.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 社員マスタ
 */
@Data
public class Employee {
    private String employee_id; // 社員ID
    private String employee_name1_last; // 社員名漢字（性）
    private String employee_cd; // 社員CD
    private String mail; // メールアドレス
    private Date joined_date; // 入社日
    private Date leave_date; // 退社日
    private String employeeattribute_id; // 社員属性ID
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}
