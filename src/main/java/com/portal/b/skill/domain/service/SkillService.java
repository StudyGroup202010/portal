package com.portal.b.skill.domain.service;

import java.util.List;

import com.portal.b.common.domain.model.Career;
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
    public List<Skill> selectSkillMany();

    /**
     * １件取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return skill
     */
    public Skill selectSkillOne(String employee_id);

    /**
     * １件取得用メソッド.
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return career
     */
    public Career selectCareerOne(String employee_id, String certification_no);

    /**
     * 業務経歴登録用メソッド
     * 
     * @param career career
     * @return true/false
     */
    public boolean insertCareerOne(Career career);

    /**
     * １件更新用メソッド.
     * 
     * @param skill skill
     * @return true/false
     */
    public boolean updateSkillOne(Skill skill);

    /**
     * １件更新用メソッド.
     * 
     * @param career career
     * @return true/false
     */
    public boolean updateCareerOne(Career career);

    /**
     * １件削除用メソッド.
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return true/false
     */
    public boolean deleteCareerOne(String employee_id, String certification_no);

    /**
     * スキル情報条件検索用メソッド.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param biko                biko
     * @return SkillList
     */
    public List<Skill> selectSkillBy(String employee_cd, String employee_name1_last, String biko);

    /**
     * 業務経歴条件検索用メソッド.
     * 
     * @param employee_id      employee_id
     * @param business_content business_content
     * @param biko             biko
     * @return CareerList
     */
    public List<Career> selectCareerBy1(String employee_id, String business_content, String biko);

    /**
     * 詳細情報取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return CareerList
     */
    public List<Career> selectCareerBy2(String employee_id);
}
