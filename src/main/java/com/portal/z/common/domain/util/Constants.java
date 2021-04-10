package com.portal.z.common.domain.util;

import org.springframework.stereotype.Component;

/**
 * 定数定義
 *
 */
@Component("Constants")
public final class Constants {

    /**
     * ログイン失敗回数の最大値<BR>
     * ・環境マスタ設定値。<BR>
     * ・この値を超えるとアカウントがロックされます。<BR>
     * ・初期値は１回。
     */
    public static final String LOGIN_MISS_TIMES_MAX = "1";

    /**
     * パスワード有効期間<BR>
     * ・環境マスタ設定値。<BR>
     * ・パスワードの有効期限の初期値（月数）<BR>
     * ・初期値は１か月。
     */
    public static final int PASS_UPDATE_NXT = 1;

    /**
     * 認証情報有効期間<BR>
     * ・認証情報の有効期限（分）<BR>
     * ・初期値は３０分。
     */
    public static final int EXPIRYDATE_NXT = 30;

    /**
     * 秘密情報桁数<BR>
     * ・秘密情報を生成する時に指定する桁数<BR>
     * ・初期値は１０桁。
     */
    public static final int SECRET_LEN = 10;

    /**
     * アプリケーションURL<BR>
     * ・このアプリケーションのURL<BR>
     * ・初期値は"http://localhost:8080/"。
     */
    public static final String APPLICATION_URL = "http://localhost:8080/";

    /**
     * エラーメッセージ未登録用メッセージ<BR>
     */
    public static final String NOT_FOUND_MESSAGE = "エラーメッセージが登録されていません。";
    
    /**
     * エクセルテンプレート<BR>
     */
    public static final String EXCEL_TEMPLATE = "src/main/resources/templates/excel/";

    /**
     * View用メッセージ<BR>
     */
    public static final String NOT_FOUND_TEMPLATE = "エクセルテンプレートが開けませんでした。";
    public static final String NOT_FOUND_SHEET = "シートが開けませんでした。";

    /**
     * ユーザ一覧CSVファイル名<BR>
     * ・ユーザ一覧画面から出力するCSVファイルの名称<BR>
     */
    public static final String USERLIST_CSVNAME = "userlist.csv";

    /**
     * ロール名<BR>
     * ・環境マスタ設定値。<BR>
     * ・一般用と管理者用を登録する。<BR>
     * ・Spring Securityの認可処理は、"ROLE_"で始まる権限情報をロールとして扱うので、必ず"ROLE_"で始まること。
     */
    public static enum ROLE_NAME {
        /**
         * 一般用ロール(ROLE_GENERAL)
         */
        ROLE_NAME_G,
        /**
         * 管理者用ロール(ROLE_ADMIN)
         */
        ROLE_NAME_A
    };

    /**
     * メール送信可否フラグ<BR>
     * ・環境マスタ設定値。
     *
     */
    public static enum SEND_MAIL {
        /**
         * メール送信可
         */
        SEND_MAIL_ENABLE,
        /**
         * メール送信不可
         */
        SEND_MAIL_DISABLE
    };

    /**
     * メール送信用SMTPの設定<BR>
     * ・環境マスタ設定値。
     *
     */
    public static enum MAIL_SMTP {
        /**
         * SMTPホストアドレス
         */
        MAIL_SMTP_HOST,
        /**
         * SMTPホストポート番号
         */
        MAIL_SMTP_PORT,
        /**
         * SMTPログインユーザ名
         */
        MAIL_SMTP_USERNAME,
        /**
         * SMTPログインユーザパスワード
         */
        MAIL_SMTP_PASSWORD,
        /**
         * SMTP認証
         */
        MAIL_SMTP_AUTH,
        /**
         * TLS接続 TLSによる接続が必要かどうか 必要な場合はTRUEをセットする
         */
        MAIL_SMTP_STARTTLS_ENABLE
    }

    /**
     * メール関係設定<BR>
     * ・環境マスタ設定値。
     */
    public static enum MAIL_ENV {
        /**
         * 管理者用メールアドレス
         */
        MAIL_ADMIN_CONTACT,
        /**
         * 問い合わせ用メールのタイトル
         */
        MAIL_TITLE_CONTACT,
        /**
         * パスワード再設定用メールのタイトル
         */
        MAIL_TITLE_PWREISSUE
    };

}
