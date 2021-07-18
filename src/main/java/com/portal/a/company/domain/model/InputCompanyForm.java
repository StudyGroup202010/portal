package com.portal.a.company.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 会社マスタ情報の入力用Form
 */
@Data
public class InputCompanyForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String company_cd; // 会社CD
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String company_name; // 会社名

    private String biko; // 備考
}
