package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 資格情報
 *
 */
@Data
public class Certification {
    private String certification_id;// 資格ID
    private String certification_name;// 資格名
    private String organizer_name;// 主催団体名
    private String disp_order;// 表示順
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}