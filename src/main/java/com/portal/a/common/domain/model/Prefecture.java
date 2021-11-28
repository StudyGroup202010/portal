package com.portal.a.common.domain.model;

import lombok.Data;

/**
 * 都道府県
 *
 */
@Data
public class Prefecture {
    private String prefCode; // 都道府県名コード
    private String pref; // 都道府県名 漢字
    private String halfWidthKana; // 都道府県名 半角カタカナ
    private String fullWidthKana; // 都道府県名 全角カタカナ
    private String hiragana; // 都道府県名 ひらがな
    private int latitude; // ロケーション 緯度
    private int longitude; // ロケーション 緯度
}