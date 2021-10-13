package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.b.common.domain.model.Career;

/**
 * CareerMapper
 *
 */
@Mapper
public interface CareerMapper {

    /**
     * 登録用メソッド
     * 
     * @param chareer chareer
     * @return true/false
     */
    public boolean insertOne(Career chareer);

    /**
     * １件検索用メソッド
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return Career
     */
    public Career selectOne(String employee_id, String certification_no);

    /**
     * １件更新用メソッド
     * 
     * @param career career
     * @return true/false
     */
    public boolean updateOne(Career career);

    /**
     * １件削除用メソッド
     * 
     * @param employee_id      employee_id
     * @param certification_no certification_no
     * @return true/false
     */
    public boolean deleteOne(String employee_id, String certification_no);

    /**
     * 条件検索用メソッド
     * 
     * @param employee_id      employee_id
     * @param business_content business_content
     * @param biko             biko
     * @return CareerList
     */
    public List<Career> selectBy1(String employee_id, String business_content, String biko);

    /**
     * 条件検索用「メソッド
     * 
     * @param employee_id employee_id
     * @return CareerList
     */
    public List<Career> selectBy2(String employee_id);
}
