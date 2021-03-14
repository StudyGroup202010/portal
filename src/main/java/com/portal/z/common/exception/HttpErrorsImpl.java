//package com.portal.z.common.exception;
//
//import org.springframework.http.HttpStatus;
//
///**
// * エラーの一覧。
// */
//public enum HttpErrorsImpl implements HttpErrors {
//    /**
//     * 環境マスタ未登録エラー用メッセージ
//     */
//    NOTFOUND_ENV(HttpStatus.INTERNAL_SERVER_ERROR, "「{0}」が環境マスタに登録されていません。"),
//    /**
//     * メールアドレス未登録エラー用メッセージ
//     */
//    NOTSET_MAILADRESS(HttpStatus.INTERNAL_SERVER_ERROR, "「{0}」にメールアドレスが登録されていません。"),
//    /**
//     * データ重複エラー用メッセージ
//     */
//    DUPLICATED(HttpStatus.INTERNAL_SERVER_ERROR, "「{0}」は既に登録されています。"),
//    /**
//     * 整合性制約エラー用メッセージ
//     */
//    DATAINTEGRITY(HttpStatus.INTERNAL_SERVER_ERROR, "「{0}」は存在しません。"),
//    /**
//     * 想定外エラー用メッセージ
//     */
//    UNEXPECTED(HttpStatus.INTERNAL_SERVER_ERROR, "想定外のエラーが発生しました。 : {0}");
//
//    /** HTTPステータス */
//    protected HttpStatus status;
//
//    /** メッセージ */
//    protected String message;
//
//    /**
//     * コンストラクタ
//     * 
//     * @param status  ステータス
//     * @param message メッセージ
//     */
//    private HttpErrorsImpl(HttpStatus status, String message) {
//        this.status = status;
//        this.message = message;
//    }
//
//    @Override
//    public HttpStatus getStatus() {
//        return status;
//    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
//}