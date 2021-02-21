package com.portal.z.common.domain.logic;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.util.Constants;
import com.sun.mail.util.MailConnectException;

import lombok.extern.slf4j.Slf4j;

/**
 * メール送信用の共通クラス
 *
 */
@Component("MailSend")
@Slf4j
public class MailSend {

    @Autowired
    private EnvMapper envMapper;

    /**
     * メール送信用メソッド
     * 
     * @param sendFrom 送信者のアドレス
     * @param sendTo   受信者のアドレス
     * @param Subject  メールタイトル
     * @param text     メール本文
     * @return 送信成功：true 送信失敗:false
     * @throws MessagingException 送信エラー
     */
    public boolean mailsendregister(String sendFrom, String sendTo, String Subject, String text)
            throws MessagingException {

        // 環境マスタに登録したメール送信可否フラグを取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env sendmail = envMapper.selectOne(Constants.SEND_MAIL.SEND_MAIL_ENABLE.name());
        if (sendmail == null) {
            log.info("メール送信可否フラグ取得失敗");
            return false;
        }

        // 環境マスタに登録したSMTPホストアドレスを取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env host = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_HOST.name());
        if (host == null) {
            log.info("SMTPホストアドレス取得失敗");
            return false;
        }

        // 環境マスタに登録したSMTPポート番号を取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env port = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_PORT.name());
        if (port == null) {
            log.info("SMTPポート番号取得失敗");
            return false;
        }

        // 環境マスタに登録したSMTPログインユーザ名を取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env username = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_USERNAME.name());
        if (username == null) {
            log.info("SMTPログインユーザ名取得失敗");
            return false;
        }

        // 環境マスタに登録したSMTPログインパスワードを取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env password = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_PASSWORD.name());
        if (password == null) {
            log.info("SMTPログインパスワード取得失敗");
            return false;
        }

        // 環境マスタに登録したSMTP認証を取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env auth = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_AUTH.name());
        if (auth == null) {
            log.info("SMTP認証取得失敗");
            return false;
        }

        // 環境マスタに登録したTLS接続を取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Env starttls = envMapper.selectOne(Constants.MAIL_SMTP.MAIL_SMTP_STARTTLS_ENABLE.name());
        if (starttls == null) {
            log.info("TLS接続取得失敗");
            return false;
        }

        // セッション用作成用の接続情報を設定する
        Properties props = new Properties();
        props.put("mail.smtp.host", host.getEnv_txt());
        props.put("mail.smtp.port", port.getEnv_txt());
        props.put("mail.smtp.username", username.getEnv_txt());
        props.put("mail.smtp.password", password.getEnv_txt());
        props.put("mail.smtp.auth", auth.getEnv_txt());
        props.put("mail.smtp.starttls.enable", starttls.getEnv_txt());

        // セッションを作成する。
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.smtp.username"),
                        props.getProperty("mail.smtp.password"));
            }
        });

        try {
            // セッションを生成する。
            MimeMessage message = new MimeMessage(session);
            // メール送信者（実際は接続情報で設定したmail.smtp.usernameから送付されます）
            message.setFrom(sendFrom);
            // メール送付先
            message.setRecipients(Message.RecipientType.TO, sendTo);
            // メールタイトル
            message.setSubject(Subject);
            // メール本文
            message.setText(text);

            Transport.send(message);

            return true;

        } catch (MailConnectException | AuthenticationFailedException e) {

            log.info("エラー発生" + e);

            return false;
        }
    }
}
