package com.portal.z.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.Columns;

/**
 * ColumnsMapper
 *
 */
@Mapper
public interface ColumnsMapper {
    /**
     * １件検索用メソッド
     * 
     * @param tablename  確認したいテーブルの名称
     * @param columnname 確認したい項目の名称
     * @return Columns
     */
    public Columns selectOne(String tablename, String columnname);
}
