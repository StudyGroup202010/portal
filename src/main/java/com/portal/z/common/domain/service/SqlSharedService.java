package com.portal.z.common.domain.service;

/**
 * SQL用共通サービス
 *
 */
public interface SqlSharedService {

    /**
     * テーブルの文字列カラムの桁数を取得する
     * 
     * @param tablename  テーブル名
     * @param columnname 文字列項目名
     * @return 指定した項目の桁数（文字列以外の項目の場合は0）
     */
    public int getstrcolumnlength(String tablename, String columnname);
}
