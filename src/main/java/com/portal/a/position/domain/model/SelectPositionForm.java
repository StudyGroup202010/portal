package com.portal.a.position.domain.model;

import lombok.Data;

/**
 * 役職マスタ情報の検索用Form
 */
@Data
public class SelectPositionForm {
    private String position_name; // 役職名
    private String start_yearmonth; // 開始年月
    private String end_yearmonth; // 最終年月
    private String biko; // 備考
}
