package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 技術マスタ
 *
 */
@Data
public class Technology {
    private String technology_id;// 技術ID
    private String technology_name;// 技術名
    private String technology_kbn;// 技術区分
    private String biko;// 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}