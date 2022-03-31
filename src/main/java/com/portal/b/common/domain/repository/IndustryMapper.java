package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.portal.b.common.domain.model.Industry;

/**
 * IndustryMapper
 *
 */
@Mapper
public interface IndustryMapper {

    /**
     * 登録用メソッド
     * 
     * @param industry industry
     * @return true/false
     */
    public boolean insertOne(Industry industry);

    /**
     * １件検索用メソッド
     * 
     * @param industry_id industry_id
     * @return Industry
     */
    public Industry selectOne(String industry_id);

    /**
     * 全件検索用メソッド
     * 
     * @return IndustryList
     */
    public List<Industry> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param industry industry
     * @return true/false
     */
    public boolean updateOne(Industry industry);

    /**
     * １件削除用メソッド
     * 
     * @param industry_id industry_id
     * @return true/false
     */
    public boolean deleteOne(String industry_id);

}
