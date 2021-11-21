package com.portal.z.common.domain.util;

import java.text.Normalizer;

import com.ibm.icu.text.Transliterator;

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
     * サロゲートペア、Unicode結合文字の問題：str.length()を使うと"吉田太郎"が５文字で返ってしまう。（本当は４が返ってほしい）
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
     * サロゲートペアの問題："𠮷田 太郎"をsubstring(0, 2)で切り出すと"𠮷"になってしまう<BR>
     * ⇒対応済<BR>
     * 例）"𠮷田太郎"のとき、開始位置：0、終了位置：2で指定すると、"𠮷田"となる。
     * 
     * @param str        分割したい文字列
     * @param startIndex 分割開始位置
     * @param endIndex   分割終了位置
     * @return 分割した文字列<BR>
     *         strがnullの時はnull<BR>
     *         endIndex より startIndex が大きいときは""<BR>
     *         startIndexかendIndexのどちらかが文字列長より大きいときは""<BR>
     *         startInd = endIndexのときは""
     */
    public static String getSubstring(String str, int startIndex, int endIndex) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        if (endIndex < startIndex) {
            return "";
        }

        if (getStrLength(str) < endIndex) {
            return "";
        }

        if (getStrLength(str) < endIndex) {
            return "";
        }

        int startIndexSurrogate = str.offsetByCodePoints(0, startIndex);
        int endIndexSurrogate = str.offsetByCodePoints(0, endIndex);

        return str.substring(startIndexSurrogate, endIndexSurrogate);
    }

    /**
     * 全て全角カナかどうかをチェックする<BR>
     * 
     * @param str str
     * @return true：全て全角カタカナ false：全角カタカナ以外の文字が含まれている
     */
    public static boolean isFullkana(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String IS_FULL_KANA = "^[\\u30A0-\\u30FF]+$";
        return str.matches(IS_FULL_KANA);
    }

    /**
     * 全角カナをローマ字（大文字）に変換する。<BR>
     * 
     * @param str str
     * @return 変換したローマ字（大文字） null:引数がnullもしくは全角カナではない。
     */
    public static String getLatinFromFullkana(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (isFullkana(str) == false) {
            // 全角カナでなければ処理をしない。
            return null;
        }

        // ローマ字に変換
        Transliterator trans = Transliterator.getInstance("Katakana-Latin");
        str = trans.transliterate(str);

        // 大文字に変換
        trans = Transliterator.getInstance("Any-Upper");

        return trans.transliterate(str);
    }

}
