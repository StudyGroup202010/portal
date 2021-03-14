package com.portal.z.common.exception;

/**
 * アプリケーション例外クラス。
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** メッセージID */
    protected String message_id;

    /** メッセージ */
    protected String message;

    /** 例外原因 */
    protected Throwable cause;

    /**
     * コンストラクタ。
     * 
     * @param message_id メッセージID
     * @param message    メッセージ
     */
    public ApplicationException(String message_id, String message) {
        super();
        this.message_id = message_id;
        this.message = message;
    }

    /**
     * コンストラクタ。
     * 
     * @param message_id メッセージID
     * @param message    メッセージ
     * @param cause      cause
     */
    public ApplicationException(String message_id, String message, Throwable cause) {
        super(cause);
        this.message_id = message_id;
        this.message = message;
        this.cause = cause;
    }

    /**
     * メッセージID返します。
     * 
     * @return メッセージID
     */
    public String getMessage_id() {
        return this.message_id;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}