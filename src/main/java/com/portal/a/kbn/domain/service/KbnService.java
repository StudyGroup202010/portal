package com.portal.a.kbn.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Kbn;

/**
 * KbnService のインターフェース
 */
public interface KbnService {

    /**
     * 全件取得用メソッド.
     * 
     * @return 区分データクラスのList
     */
    public List<Kbn> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param kbn 区分データクラス
     * @return 区分データクラスのList
     */
    public Kbn selectOne(Kbn kbn);

    /**
     * 登録用メソッド
     * 
     * @param kbn 区分データクラス
     * @return 処理結果(true/false)
     */
    public boolean insertOne(Kbn kbn);

    /**
     * １件更新用メソッド.
     * 
     * @param kbn 区分データクラス
     * @return 処理結果(true/false)
     */
    public boolean updateOne(Kbn kbn);

    /**
     * １件削除用メソッド.
     * 
     * @param kbn 区分データクラス
     * @return 処理結果(true/false)
     */
    public boolean deleteOne(Kbn kbn);

    /**
     * 条件検索用メソッド.
     * 
     * @param kbn 区分データクラス
     * @return 区分データクラスのList
     */
    public List<Kbn> selectBy(Kbn kbn);
}
