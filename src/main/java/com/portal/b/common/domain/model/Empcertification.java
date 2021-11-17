package com.portal.b.common.domain.model;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 社員資格
 *
 */
@Data
public class Empcertification {
    private String employee_id;// 社員ID
    private String certification_id; // 資格ID
    private Date acquisition_date; // 取得日
    private String certification_name;// 資格名
    private String careercertification_id; // チェック用資格ID
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}