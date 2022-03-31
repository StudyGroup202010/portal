package com.portal.z.common.domain.util;

import org.springframework.stereotype.Component;

/**
 * 定数定義
 *
 */
@Component("Constants")
public final class Constants {

    // 他でインスタンス化されないようにprivateでコンストラクタを定義する。
    private Constants() {
    };

    /**
     * アプリケーションURL<BR>
     * ・このアプリケーションのURL<BR>
     * ・初期値は"http://localhost:8080/"。
     */
    public static final String APPLICATION_URL = "http://localhost:8080/";
    
    /**
     * CORSで許可を与えるオリジン<BR>
     * ・初期値は"http://localhost:4200"。
     */
    public static final String CORS_ALLOWED_ORIGINS_1 = "http://localhost:4200";

    /**
     * エラーメッセージ未登録用メッセージ<BR>
     * ・初期値は"エラーメッセージが登録されていません。"
     */
    public static final String NOT_FOUND_MESSAGE = "エラーメッセージが登録されていません。";

    /**
     * ログイン失敗回数の最大値<BR>
     * ・環境マスタ設定値。<BR>
     * ・この値を超えるとアカウントがロックされます。<BR>
     * ・初期値は１回。
     */
    public static final String LOGIN_MISS_TIMES_MAX = "1";

    /**
     * ユーザ有効期間<BR>
     * ・ユーザ有効期間の初期値（年数）<BR>
     * ・初期値は10年。
     */
    public static final int USER_DUE_DATE_EXPIRATION_DATE = 10;

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
     * 初期パスワード<BR>
     * ・ユーザ登録時に初期設定するパスワード<BR>
     */
    public static final String INITIAL_PASSWORD = "password";

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

    /**
     * エクセルテンプレートの場所<BR>
     * ・初期値は"src/main/resources/templates/excel/"
     */
    public static final String EXCEL_TEMPLATE = "src/main/resources/templates/excel/";

    /**
     * View用メッセージ<BR>
     * ・エクセルテンプレートが存在しないとき。<BR>
     * ・初期値は"エクセルテンプレートが開けませんでした。"
     */
    public static final String NOT_FOUND_TEMPLATE = "エクセルテンプレートが開けませんでした。";

    /**
     * View用メッセージ<BR>
     * ・エクセルテンプレートが存在しないとき。<BR>
     * ・初期値は"シートが開けませんでした。"
     */
    public static final String NOT_FOUND_SHEET = "エクセルテンプレートのシートが開けませんでした。";

    /**
     * 性別区分<BR>
     * ・性別（男）を指定する値<BR>
     */
    public static final String GENDER_KBN_MAN = "M";

    /**
     * 性別区分<BR>
     * ・性別（女）を指定する値<BR>
     */
    public static final String GENDER_KBN_WOMAN = "W";

    /**
     * 技術区分<BR>
     * ・開発言語<BR>
     */
    public static final String TECHNOLOGY_KBN_LANG = "01";

    /**
     * 技術区分<BR>
     * ・OS<BR>
     */
    public static final String TECHNOLOGY_KBN_OS = "02";

    /**
     * 技術区分<BR>
     * ・DB<BR>
     */
    public static final String TECHNOLOGY_KBN_DB = "03";

    /**
     * 業務経歴書ファイル名<BR>
     * 
     */
    public static final String SKILLREPORT_NAME = "業務経歴書";

    /**
     * 業務経歴書 業務経歴追記開始行<BR>
     * 
     * 業務経歴書に記述する業務経歴欄の開始行を定義<BR>
     * 
     */
    public static final int SKILLREPORT_CAREER_START = 15;

    /**
     * 業務経歴書 業務経歴行の高さ<BR>
     * 
     * 業務経歴書に記述する業務経歴欄の行の高さを定義<BR>
     * 
     */
    public static final int SKILLREPORT_CAREER_HEIGHT = 29;

    /**
     * 業務経歴書 終了年月初期値<BR>
     * 
     * 終了年月未記載で現在の業務を続けている場合の表示
     * 
     */
    public static final String SKILLREPORT_END_YEARMONTH = "現在";

    /**
     * 業務経歴書 業務経歴行の初期表示行数<BR>
     * 
     * 業務経歴書の業務経歴表の最低表示行数を定義<BR>
     * 
     */
    public static final int SKILLREPORT_CAREER_DEFAULT_COUNT = 5;

    /**
     * 業務経歴書 業務経歴行の最大表示行数<BR>
     * 
     * 業務経歴書の業務経歴表の最大表示行数を定義<BR>
     * 
     */
    public static final int SKILLREPORT_CAREER_MAX_COUNT = 49;

    /**
     * 社員マスタ 退職者検索可否フラグ<BR>
     * 
     * 社員マスタを検索するときに退職者を表示するときの定義<BR>
     * 初期値は"1"
     * 
     */
    public static final String LEAVE_FLG = "1";

}
