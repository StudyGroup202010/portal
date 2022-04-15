package com.portal.a.organization.domain.model;

import lombok.Data;

/**
 * 組織マスタ情報の検索用Form
 */
@Data
public class SelectOrganizationForm {
    private String organization_name; // 組織名
    private String company_cd; // 会社名
    private String start_yearmonth; // 開始年月
    private String end_yearmonth; // 最終年月
    private String biko; // 備考
}
