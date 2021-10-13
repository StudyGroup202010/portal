package com.portal.b.skill.domain.model;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.portal.b.common.domain.model.Career;

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
    @Size(max = 6, groups = { ValidUpdate1.class }, message = "{length_check}")
    @Pattern(regexp = "[0-9]*", groups = { ValidUpdate1.class }, message = "{numerical_check}") // 数字であること
    private String graduation_date;// 卒業年月
    private String notices; // 特記事項
    private String biko; // 備考
    private List<Career> careerlist;
}
