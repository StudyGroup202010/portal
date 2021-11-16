package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.b.common.domain.model.Technology;

/**
 * TechnologyMapper
 *
 */
@Mapper
public interface TechnologyMapper {

    /**
     * 登録用メソッド
     * 
     * @param technology technology
     * @return true/false
     */
    public boolean insertOne(Technology technology);

    /**
     * １件検索用メソッド
     * 
     * @param technology_id technology_id
     * @return Technology
     */
    public Technology selectOne(String technology_id);

    /**
     * 全件検索用メソッド
     * 
     * @return TechnologyList
     */
    public List<Technology> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param technology technology
     * @return true/false
     */
    public boolean updateOne(Technology technology);

    /**
     * １件削除用メソッド
     * 
     * @param technology_id technology_id
     * @return true/false
     */
    public boolean deleteOne(String technology_id);

    /**
     * 条件検索用メソッド
     * 
     * @param technology_kbn technology_kbn
     * @return TechnologyList
     */
    public List<Technology> selectBy(String technology_kbn);

}
