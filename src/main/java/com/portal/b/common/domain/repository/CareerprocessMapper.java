package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.b.common.domain.model.Careerprocess;

/**
 * CareerprocessMapper
 *
 */
@Mapper
public interface CareerprocessMapper {

    /**
     * 登録用メソッド
     * 
     * @param careerprocess careerprocess
     * @return true/false
     */
    public boolean insertOne(Careerprocess careerprocess);

    /**
     * 条件削除用メソッド
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
     * @param certification_no certification_no
     * @return CareerprocessList
     */
    public List<Careerprocess> selectBy(String employee_id, String certification_no);
}
