package com.portal.z.common.validation;

import org.passay.CharacterData;

/**
 * passayでチェックに使用するSpecialCharacterDataを定義するenum
 * 
 * パスワードでチェックする記号を定義する。
 */
public enum SpecialCharacterData implements CharacterData {

    /**
     * チェック対象の記号の設定<br>
     * 第1引数はメッセージ定数(メッセージプロパティの定数と揃える)<br>
     * 第2引数はチェック対象の記号<br>
     */
    Special("NO_SPECIAL_CHAR", "-_!@");

    /** エラーコード(message.Propertiesで参照) */
    private final String errorCode;
    /** チェックする記号 */
    private final String characters;

    /**
     * コンストラクタ
     *
     * @param code       Error code.
     * @param charString Characters as string.
     */
    SpecialCharacterData(final String code, final String charString) {
        errorCode = code;
        characters = charString;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getCharacters() {
        return characters;
    }
}
