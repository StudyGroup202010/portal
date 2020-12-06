package com.portal.z.common.exception;

/**
 * RESTエラークラス。
 */
public class RestError {
    /** HTTPステータス */
    public int status;

    /** エラーコード */
    public String error;

    /** メッセージ */
    public String message;

    /** 例外クラス */
    public String exception;

    /** 処理パス */
    public String path;
}