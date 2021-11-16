package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 会社マスタ
 *
 */
@Data
public class Company {
    private String company_cd; // 会社CD
    private String company_name; // 会社名
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}