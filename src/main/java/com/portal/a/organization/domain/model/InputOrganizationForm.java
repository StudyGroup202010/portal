package com.portal.a.organization.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 組織マスタ情報の入力用Form
 */
@Data
public class InputOrganizationForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String organization_cd; // 組織CD
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String organization_name; // 組織名
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String company_cd; // 会社CD
    
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String start_yearmonth; // 開始年月

    // 必須入力
    @NotBlank(message = "{require_check}")
    private String end_yearmonth; // 最終年月
    
    private String biko; // 備考
}
