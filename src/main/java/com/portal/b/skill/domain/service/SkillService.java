package com.portal.b.skill.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Employee;
import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Careerprocess;
import com.portal.b.common.domain.model.Careertechnology;
import com.portal.b.common.domain.model.Empcertification;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.common.domain.model.Technology;

/**
 * SkillService
 *
 */
public interface SkillService {

    // スキル情報
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
     * １件更新用メソッド.
     * 
     * @param skill skill
     * @return true/false
     */
    public boolean updateSkillOne(Skill skill);

    /**
     * １件削除用メソッド.
     * 
     * @param employee_id employee_id
     * @return true/false
     */
    public boolean deleteSkillOne(String employee_id);

    /**
     * スキル情報条件検索用メソッド.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param organization_name   organization_name
     * @param biko                biko
     * @return SkillList
     */
    public List<Skill> selectSkillBy(String employee_cd, String employee_name1_last, String organization_name,
            String biko);

    /**
     * スキル情報条件検索用EXCELメソッド.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param organization_name   organization_name
     * @param biko                biko
     * @return SkillList
     */
    public List<Skill> selectSkillByExcel(String employee_cd, String employee_name1_last, String organization_name,
            String biko);

    // 業務経歴
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
     * 業務経歴条件検索用メソッド.
     * 
     * @param employee_id      employee_id
     * @param business_content business_content
     * @param biko             biko
     * @return CareerList
     */
    public List<Career> selectCareerBy1(String employee_id, String business_content, String biko);

    /**
     * 経歴番号取得用メソッド.
     * 
     * @return Career
     */
    public Career selectCareerBy2();

    // 技術マスタ
    /**
     * 技術マスタ条件検索用メソッド.
     * 
     * @param technology_kbn technology_kbn
     * @return TechnologyList
     */
    public List<Technology> selectTechnologyBy(String technology_kbn);

    // 業務経歴技術
    /**
     * 業務経歴技術条件検索用メソッド.
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @param technology_kbn   technology_kbn
     * @return CareertechnologyList
     */
    public List<Careertechnology> selectCareertechnologyBy(String employee_id, String certification_no,
            String technology_kbn);

    /**
     * 業務経歴技術登録用メソッド
     * 
     * @param careertechnology careertechnology
     * @return true/false
     */
    public boolean insertCareertechnologyOne(Careertechnology careertechnology);

    /**
     * 業務経歴技術削除用メソッド
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return true/false
     */
    public boolean deleteCareertechnologyOne(String employee_id, String certification_no);

    // 工程マスタ
    /**
     * 工程マスタ条件検索用メソッド.
     * 
     * @return TechnologyList
     */
    public List<Process> selectProcessMany();

    // 業務経歴工程
    /**
     * 業務経歴工程条件検索用メソッド.
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return CareerprocessList
     */
    public List<Careerprocess> selectCareerprocessBy(String employee_id, String certification_no);

    /**
     * 業務経歴工程登録用メソッド
     * 
     * @param careerprocess careerprocess
     * @return true/false
     */
    public boolean insertCareerprocessOne(Careerprocess careerprocess);

    /**
     * 業務経歴工程削除用メソッド
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return true/false
     */
    public boolean deleteCareerprocessOne(String employee_id, String certification_no);

    // 社員情報
    /**
     * １件取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return employee
     */
    public Employee selectEmployeeOne(String employee_id);

    // 社員資格
    /**
     * 社員資格検索用メソッド.
     * 
     * @param employee_id employee_id
     * @return CareertechnologyList
     */
    public List<Empcertification> selectEmpcertificationBy(String employee_id);

    /**
     * 社員資格削除用メソッド
     * 
     * @param employee_id employee_id
     * @return true/false
     */
    public boolean deleteEmpcertificationOne(String employee_id);

    /**
     * 社員資格登録用メソッド
     * 
     * @param empcertification empcertification
     * @return true/false
     */
    public boolean insertEmpcertificationOne(Empcertification empcertification);
}
