package com.portal.b.skill.domain.model;

import lombok.Data;

/**
 * 業務経歴情報の検索用Form
 */
@Data
public class SelectCareerForm {
    private String employee_id;// 社員ID
    private String business_content;// 業務内容
    private String biko; // 備考
    private String from; // 遷移元画面
}