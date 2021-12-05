package com.portal.a.company.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 会社マスタ情報の入力用Form
 */
@Data
public class InputCompanyForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    @Pattern(regexp = "^[-_0-9a-zA-Z]+$", message = "{Alphanumericsymbols_check}") // 英数記号であること
    @Size(min = 1, max = 5, message = "{length_check_3}")
    private String company_cd; // 会社CD

    // 必須入力
    @NotBlank(message = "{require_check}")
    private String company_name; // 会社名

    private String biko; // 備考
}
