package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 社員属性マスタ
 *
 */
@Data
public class Employeeattribute {
    private String employeeattribute_id; // 社員属性ID
    private String employeeattribute_name; // 社員属性名
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}