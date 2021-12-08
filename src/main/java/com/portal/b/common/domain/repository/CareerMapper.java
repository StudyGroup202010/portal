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
     * 条件検索用メソッド
     * 
     * bt003_careerに追加するときに使うcertification_noの値を取得する。
     * 業務経歴を追加するときに、業務経歴技術や業務経歴産業分類、業務経歴工程も追加するのでここで取得したcertification_noを使う。
     * 
     * @return Career
     */
    public Career selectBy2();

    /**
     * 条件検索用メソッド
     * 
     * @param employee_id employee_id
     * @return CareerList
     */
    public List<Career> selectBy3(String employee_id);
}
