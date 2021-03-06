package com.portal.z.common.domain.service;

import javax.mail.MessagingException;

/**
 * メール送信用共通サービス
 *
 */
public interface MailSendSharedService {

    /**
     * メール送信用メソッド<BR>
     * 
     * 送信元（sendFrom）と送信先（sendTo）のアドレスは必須。<BR>
     * 環境マスタに以下の値を設定しておくこと。<BR>
     * ・メール送信可否フラグ（SEND_MAIL_ENABLE）<BR>
     * ・SMTPホストアドレス（MAIL_SMTP_HOST）<BR>
     * ・SMTPポート番号（MAIL_SMTP_PORT）<BR>
     * ・SMTPログインユーザ名（MAIL_SMTP_USERNAME）<BR>
     * ・SMTPログインパスワード（MAIL_SMTP_PASSWORD）<BR>
     * ・SMTP認証（MAIL_SMTP_AUTH）<BR>
     * ・TLS接続（MAIL_SMTP_STARTTLS_ENABLE）<BR>
     * 
     * @param sendFrom 送信元のアドレス
     * @param sendTo   送信先のアドレス
     * @param Subject  メールタイトル
     * @param text     メール本文
     * @throws MessagingException 送信エラー
     */
    public void mailsendregister(String sendFrom, String sendTo, String Subject, String text)
            throws MessagingException;

}