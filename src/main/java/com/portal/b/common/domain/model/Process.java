package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 工程マスタ
 *
 */
@Data
public class Process {
    private String process_id;// 工程ID
    private String process_name;// 工程名
    private String biko;// 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}