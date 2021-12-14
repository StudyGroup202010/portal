package com.portal.a.employee.domain.model;

import lombok.Data;

/**
 * 社員マスタ情報の検索用Form
 */
@Data
public class SelectEmployeeForm {
    private String employee_cd; // 社員CD
    private String employee_name1_last; // 社員名漢字（姓）
    private String mail; // メールアドレス
    private String biko; // 備考
    private boolean leave_flg; // 退職者を含む
}
