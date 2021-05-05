package com.portal.z.common.domain.model;

import lombok.Data;

/**
 * INFORMATION_SCHEMA COLUMNS テーブル
 */
@Data
public class Columns {
    private String table_name; // テーブル名
    private String column_name; // カラム名
    private String data_type; // データ型
    private int character_maximum_length; // 文字数（文字列型）
}