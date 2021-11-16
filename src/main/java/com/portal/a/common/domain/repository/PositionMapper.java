package com.portal.a.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.a.common.domain.model.Position;

/**
 * PositionMapper
 *
 */
@Mapper
public interface PositionMapper {

    /**
     * 登録用メソッド
     * 
     * @param position position
     * @return true/false
     */
    public boolean insertOne(Position position);

    /**
     * １件検索用メソッド
     * 
     * @param position_cd position_cd
     * @return Position
     */
    public Position selectOne(String position_cd);

    /**
     * 全件検索用メソッド
     * 
     * @return PositionList
     */
    public List<Position> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param position position
     * @return true/false
     */
    public boolean updateOne(Position position);

    /**
     * １件削除用メソッド
     * 
     * @param position_cd position_cd
     * @return true/false
     */
    public boolean deleteOne(String position_cd);

    /**
     * 条件検索用メソッド
     * 
     * @param position_name   position_name
     * @param start_yearmonth start_yearmonth
     * @param end_yearmonth   end_yearmonth
     * @param biko            biko
     * @return PositionList
     */
    public List<Position> selectBy(String position_name, String start_yearmonth, String end_yearmonth, String biko);
}
