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
    @NotBlank(groups = { ValidCreate1.class }, message = "{require_check}")
    @Size(groups = { ValidCreate2.class }, min = 5, max = 5, message = "{length_check_1}") // 桁数指定
    @Pattern(groups = { ValidCreate2.class,
            ValidUpdate1.class }, regexp = "^[-_0-9a-zA-Z]+$", message = "{Alphanumericsymbols_check}") // 英数記号であること
    private String position_cd; // 役職CD

    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(groups = { ValidCreate2.class, ValidUpdate2.class }, max = 50, message = "{length_check_2}") // 桁数指定
    private String position_name; // 役職名

    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(groups = { ValidCreate2.class, ValidUpdate2.class }, min = 6, max = 6, message = "{length_check_1}") // 桁数指定
    @Pattern(groups = { ValidCreate2.class, ValidUpdate2.class }, regexp = "[0-9]*", message = "{type_check_2}") // 数字であること
    private String start_yearmonth; // 開始年月

    @Size(groups = { ValidCreate2.class, ValidUpdate2.class }, max = 6, message = "{length_check_1}") // 桁数指定
    @Pattern(groups = { ValidCreate2.class, ValidUpdate2.class }, regexp = "[0-9]*", message = "{type_check_2}") // 数字であること
    private String end_yearmonth; // 最終年月

    @Size(groups = { ValidCreate2.class, ValidUpdate2.class }, max = 100, message = "{length_check_2}")
    private String biko; // 備考
}
