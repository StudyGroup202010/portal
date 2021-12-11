package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.b.common.domain.model.Careertechnology;

/**
 * CareertechnologyMapper
 *
 */
@Mapper
public interface CareertechnologyMapper {

    /**
     * 登録用メソッド
     * 
     * @param chareertechnology chareertechnology
     * @return true/false
     */
    public boolean insertOne(Careertechnology chareertechnology);

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
     * @param technology_kbn   technology_kbn
     * @return CareertechnologyList
     */
    public List<Careertechnology> selectBy(String employee_id, String certification_no, String technology_kbn);
}
