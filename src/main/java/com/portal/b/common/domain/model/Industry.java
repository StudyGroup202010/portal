package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 産業分類マスタ
 *
 */
@Data
public class Industry {
    private String industry_id;// 産業分類ID
    private String industry_name;// 産業分類名
    private String disp_order; // 表示順
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}