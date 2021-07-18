package com.portal.a.company.domain.model;

import lombok.Data;

/**
 * 会社マスタ情報の検索用Form
 */
@Data
public class SelectCompanyForm {
    private String company_name; // 会社名
    private String biko; // 備考
}
