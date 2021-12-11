package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.portal.b.common.domain.model.Empcertification;

/**
 * EmpcertificationMapper
 *
 */
@Mapper
public interface EmpcertificationMapper {

    /**
     * 登録用メソッド
     * 
     * @param empcertification empcertification
     * @return true/false
     */
    public boolean insertOne(Empcertification empcertification);

    /**
     * 条件削除用メソッド
     * 
     * @param employee_id employee_id
     * @return true/false
     */
    public boolean deleteOne(String employee_id);

    /**
     * 条件検索用メソッド
     * 
     * @param employee_id employee_id
     * @return EmpcertificationList
     */
    public List<Empcertification> selectBy(String employee_id);
}