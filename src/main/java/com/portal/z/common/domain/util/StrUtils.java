package com.portal.z.common.domain.util;

import java.text.Normalizer;

/**
 * String関係のユーティリティ
 *
 */
public final class StrUtils {

    // 他からインスタンス起動できないよう、コンストラクタをprivateで定義する。
    private StrUtils() {

    }

    /**
     * 文字列の長さを返す<BR>
     * <BR>
     * 概要：<BR>
     * 　　　サロゲートペア、Unicode結合文字の問題：str.length()を使うと"吉田太郎"が５文字で返ってしまう。（本当は４が返ってほしい）
     * 
     * @param str 文字列
     * @return 入力した文字列の長さ
     */
    public static int getStrLength(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        String normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFC);

        return normalizedStr.codePointCount(0, normalizedStr.length());
    }

    /**
     * 文字列を指定した位置で切り出す。<BR>
     * <BR>
     * 概要：<BR>
     * 　　　サロゲートペアの問題により、"𠮷田 太郎"をsubstring(0, 2)で切り出すと"𠮷"になってしまう<BR>
     * 
     * @param str        分割したい文字列
     * @param startIndex 分割開始位置
     * @param endIndex   分割終了位置
     * @return 分割した文字列<BR>
     *         strがnullの時はnull<BR>
     *         endIndex より startIndex が大きいときはnull<BR>
     *         startIndexかendIndexのどちらかが文字列長より大きいときはnull<BR>
     *         startInd = endIndexのときは""
     */
    public static String getSubstring(String str, int startIndex, int endIndex) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        if (endIndex < startIndex) {
            return null;
        }

        if (getStrLength(str) < endIndex) {
            return null;
        }

        if (getStrLength(str) < endIndex) {
            return null;
        }

        int startIndexSurrogate = str.offsetByCodePoints(0, startIndex);
        int endIndexSurrogate = str.offsetByCodePoints(0, endIndex);

        return str.substring(startIndexSurrogate, endIndexSurrogate);
    }

}
