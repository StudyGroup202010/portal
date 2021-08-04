package com.portal.a.position.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 役職マスタ情報の入力用Form
 */
@Data
public class InputPositionForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    @Size(min = 5, max = 5,message = "{length_check_1}") // 桁数指定
    private String position_cd; // 役職CD
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    @Size(max = 50, message = "{length_check_2}") // 桁数指定
    private String position_name; // 役職名
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    @Size(min = 6, max = 6, message = "{length_check_1}") // 桁数指定
    @Pattern(regexp = "[0-9]*", message = "{type_check_2}") // 数字であること
    private String start_yearmonth; // 開始年月
    

    @Size(min = 6, max = 6, message = "{length_check_1}") // 桁数指定
    @Pattern(regexp = "[0-9]*", message = "{type_check_2}") // 数字であること
    private String end_yearmonth; // 最終年月

    @Size(max = 100, message = "{length_check_2}")
    private String biko; // 備考
}
