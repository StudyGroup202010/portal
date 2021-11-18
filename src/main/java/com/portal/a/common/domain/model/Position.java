package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 役職マスタ
 *
 */
@Data
public class Position {
    private String position_cd; // 役職CD
    private String position_name; // 役職名
    private String start_yearmonth; // 開始年月
    private String end_yearmonth; // 最終年月
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}