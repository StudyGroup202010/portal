package com.portal.a.organization.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 組織マスタ情報の入力用Form
 */
@Data
public class InputOrganizationForm {
    // 必須入力
    @NotBlank(groups = { ValidCreate1.class }, message = "{require_check}")
    @Size(groups = { ValidCreate2.class }, min = 5, max = 5, message = "{length_check_1}") // 桁数指定
    private String organization_cd; // 組織CD

    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(groups = { ValidCreate2.class, ValidUpdate2.class }, max = 50, message = "{length_check_2}") // 桁数指定
    private String organization_name; // 組織名

    private String company_cd; // 会社CD

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
