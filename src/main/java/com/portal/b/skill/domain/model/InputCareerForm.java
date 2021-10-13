package com.portal.b.skill.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 業務経歴情報の入力用Form
 */
@Data
public class InputCareerForm {
    private String employee_id;// 社員ID
    private String certification_no; // 経歴番号
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(max = 9, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check}")
    @Pattern(regexp = "[0-9]*", groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{numerical_check}") // 数字であること
    private String disp_order; // 表示順

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(min = 6, max = 6, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check}")
    @Pattern(regexp = "[0-9]*", groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{numerical_check}") // 数字であること
    private String start_yearmonth;// 開始年月

    @Size(max = 6, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check}")
    @Pattern(regexp = "[0-9]*", groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{numerical_check}") // 数字であること
    private String end_yearmonth;// 終了年月

    private String business_content;// 業務内容
    private String biko; // 備考
}
