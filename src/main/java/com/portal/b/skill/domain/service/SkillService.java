package com.portal.b.skill.domain.service;

import java.util.List;

import com.portal.b.common.domain.model.Skill;

/**
 * SkillService
 *
 */
public interface SkillService {

    /**
     * 全件取得用メソッド（社員マスタ／スキル情報）.
     * 
     * 一覧表は社員マスタから作ります。
     * 
     * @return SkillList
     */
    public List<Skill> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return skill
     */
    public Skill selectOne(String employee_id);

    /**
     * １件更新用メソッド.
     * 
     * @param skill skill
     * @return true/false
     */
    public boolean updateOne(Skill skill);

    /**
     * 条件検索用メソッド.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param biko                biko
     * @return SkillList
     */
    public List<Skill> selectBy(String employee_cd, String employee_name1_last, String biko);
}
