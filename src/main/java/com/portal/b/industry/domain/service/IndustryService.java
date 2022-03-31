package com.portal.b.industry.domain.service;

import java.util.List;

import com.portal.b.common.domain.model.Industry;

/**
 * IndustryService
 *
 */
public interface IndustryService {

    // 産業分類マスタ
    /**
     * 全件取得用メソッド（産業分類マスタ）.
     * 
     * 一覧表は産業分類マスタから作ります。
     * 
     * @return IndustryList
     */
    public List<Industry> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param industry_id industry_id
     * @return industry
     */
    public Industry selectOne(String industry_id);

    /**
     * １件更新用メソッド.
     * 
     * @param industry industry
     * @return true/false
     */
    public boolean updateOne(Industry industry);

    /**
     * １件削除用メソッド.
     * 
     * @param industry_id industry_id
     * @return true/false
     */
    public boolean deleteOne(String industry_id);

}
