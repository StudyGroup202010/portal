package com.portal.a.position.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Position;

/**
 * PositionService
 *
 */
public interface PositionService {

    /**
     * 全件取得用メソッド.
     * 
     * @return PositionList
     */
    public List<Position> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param position_cd position_cd
     * @return position
     */
    public Position selectOne(String position_cd);

    /**
     * 登録用メソッド
     * 
     * @param position position
     * @return true/false
     */
    public boolean insertOne(Position position);

    /**
     * １件更新用メソッド.
     * 
     * @param position position
     * @return true/false
     */
    public boolean updateOne(Position position);

    /**
     * １件削除用メソッド.
     * 
     * @param position_cd position_cd
     * @return true/false
     */
    public boolean deleteOne(String position_cd);

    /**
     * 条件検索用メソッド.
     * 
     * @param position_name	  position_name
     * @param start_yearmonth start_yearmonth
     * @param end_yearmonth   end_yearmonth
     * @param biko            biko
     * @return PositionList
     */
    public List<Position> selectBy(String position_name, String start_yearmonth, String end_yearmonth, String biko);
}
