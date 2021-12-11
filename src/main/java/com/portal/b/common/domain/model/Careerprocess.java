package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 業務経歴工程情報
 *
 */
@Data
public class Careerprocess {
    private String employee_id;// 社員ID
    private String certification_no; // 経歴番号
    private String process_id; // 工程ID
    private String process_name; // 工程名
    private String careerprocess_id; // チェック用業務経歴技術ID
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}