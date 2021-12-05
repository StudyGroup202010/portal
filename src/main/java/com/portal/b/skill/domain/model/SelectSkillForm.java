package com.portal.b.skill.domain.model;

import lombok.Data;

/**
 * スキル情報の検索用Form
 */
@Data
public class SelectSkillForm {
    private String employee_cd;// 社員CD
    private String employee_name1_last;// 社員名漢字（姓）
    private String organization_name;// 組織名
    private String biko; // 備考
}
