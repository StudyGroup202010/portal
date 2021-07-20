package com.portal.a.position.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 役職マスタ情報の入力用Form
 */
@Data
public class InputPositionForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String position_cd; // 役職CD
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String position_name; // 役職名
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String start_yearmonth; // 開始年月
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String end_yearmonth; // 最終年月

    private String biko; // 備考
}
