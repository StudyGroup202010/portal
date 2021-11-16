package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 組織マスタ
 *
 */
@Data
public class Organization {
    private String organization_cd; // 組織CD
    private String organization_name;// 組織名
    private String company_cd;// 会社CD
    private String start_yearmonth;// 開始年月
    private String end_yearmonth;// 最終年月
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}