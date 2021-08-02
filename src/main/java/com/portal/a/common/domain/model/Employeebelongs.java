package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 社員所属マスタ
 *
 */
@Data
public class Employeebelongs {
    private String employee_id;// 社員ID
    private String start_yearmonth;// 開始年月
    private String organization_cd;// 組織CD
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}