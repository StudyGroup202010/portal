package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.b.common.domain.model.Skill;

/**
 * SkillMapper
 *
 */
@Mapper
public interface SkillMapper {

    /**
     * 登録用メソッド
     * 
     * @param skill skill
     * @return true/false
     */
    public boolean insertOne(Skill skill);

    /**
     * １件検索用メソッド
     * 
     * @param employee_id employee_id
     * @return Skill
     */
    public Skill selectOne(String employee_id);

    /**
     * 全件検索用メソッド
     * 
     * @return SkillList
     */
    public List<Skill> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param skill skill
     * @return true/false
     */
    public boolean updateOne(Skill skill);

    /**
     * 条件検索用メソッド
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param biko                biko
     * @return SkillList
     */
    public List<Skill> selectBy(String employee_cd, String employee_name1_last, String biko);
}
