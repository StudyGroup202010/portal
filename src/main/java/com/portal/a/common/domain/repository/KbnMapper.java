package com.portal.a.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.a.common.domain.model.Kbn;

/**
 * KbnMapper
 *
 */
@Mapper
public interface KbnMapper {

    /**
     * 登録用メソッド
     * 
     * @param kbn 区分データクラス
     * @return true/false
     */
    public boolean insertOne(Kbn kbn);

    /**
     * １件検索用メソッド
     * 
     * @param kbn 区分データクラス
     * @return Kbn
     */
    public Kbn selectOne(Kbn kbn);

    /**
     * 全件検索用メソッド
     * 
     * @return 区分データクラスのList
     */
    public List<Kbn> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param kbn 区分データクラス
     * @return true/false
     */
    public boolean updateOne(Kbn kbn);

    /**
     * １件削除用メソッド
     * 
     * @param kbn 区分データクラス
     * @return true/false
     */
    public boolean deleteOne(Kbn kbn);

    /**
     * 条件検索用メソッド
     * 
     * @param kbn 区分データクラス
     * @return KbnList
     */
    public List<Kbn> selectBy(Kbn kbn);
}

