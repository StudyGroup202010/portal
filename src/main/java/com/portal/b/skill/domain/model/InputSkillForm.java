package com.portal.b.skill.domain.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * スキル情報の入力用Form
 */
@Data
public class InputSkillForm {
    private String employee_id;// 社員ID
    private String employee_cd;// 社員CD
    private String employee_name1_last;// 社員名漢字（姓）
    private String employee_name1_first;// 社員名漢字（名）
    private String employee_id_skill;// スキル情報社員ID
    private String final_education;// 最終学歴
    private String department;// 学科
    private String graduation_date;// 卒業年月
    private String notices; // 特記事項
    private String[] certification_id;// 資格ID
    private LocalDate[] acquisition_date;// 資格取得日
    private String biko; // 備考
    private String from; // 遷移元画面
}
