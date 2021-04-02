package com.portal.z.pwreissue.domain.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.repository.PwreissueinfoMapper;
import com.portal.z.common.domain.service.MailSendSharedService;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.exception.ApplicationException;

import lombok.extern.slf4j.Slf4j;

/**
 * パスワード再設定画面用のService
 *
 */
@Transactional
@Slf4j
@Service
public class PwreissueServiceImpl implements PwreissueService {

    @Autowired
    private PwreissueinfoMapper pwreissueinfoMapper;

    @Autowired
    MailSendSharedService mailSendSharedService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private EnvMapper envMapper;

    @Autowired
    private MassageUtils massageUtils;

    public String insertPwreissueinfo(String user_id) throws MessagingException {

        // トークンを生成
        String token = UUID.randomUUID().toString();

        // 秘密情報条件設定
        // UpperCase（半角英大文字を一文字以上含む）
        // LowerCase（半角英小文字を一文字以上含む）
        // Digit（半角数字を一文字以上含む）
        List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));

        // 秘密情報生成
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String rowSecret = passwordGenerator.generatePassword(Constants.SECRET_LEN, rules);
        String encodeSecret = passwordEncoder.encode(rowSecret); // 暗号化

        // 認証情報有効期限を計算（認証情報有効期間の初期値を使用）
        LocalDateTime localdatetime = LocalDateTime.now();
        Timestamp expirydate = Timestamp.valueOf(dateUtils.calcDate(localdatetime, "MI", Constants.EXPIRYDATE_NXT));

        // パスワード再発行情報クラスに設定
        Pwreissueinfo pwreissueinfo = new Pwreissueinfo();
        pwreissueinfo.setToken(token); // トークン
        pwreissueinfo.setUser_id(user_id); // ユーザID
        pwreissueinfo.setSecret(encodeSecret); // 秘密情報
        pwreissueinfo.setExpirydate(expirydate); // 認証情報有効期限

        try {
            // パスワード再発行情報に追加
            pwreissueinfoMapper.insertOne(pwreissueinfo);

        } catch (DataIntegrityViolationException e) {
            // 参照整合性エラーが発生した時はビジネス例外として返す。
            String messageKey = "e.co.fw.2.004";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { user_id }), e);
        }

        // パスワード再設定画面のURLをメールで送信する。
        Pwreissuemailsendregister(user_id, pwreissueinfo.getToken());

        return rowSecret;

    }

    /**
     * パスワード再設定画面のURLをメール送信
     * 
     * @param user_id 送信先メールアドレス
     * @param token   token
     * @throws MessagingException MessagingException
     */
    private void Pwreissuemailsendregister(String user_id, String token) throws MessagingException {

        // アプリケーションURLの初期値
        String APPLICATION_URL = Constants.APPLICATION_URL;

        // 環境マスタに登録したアプリケーションURLを取得
        Env env = envMapper.selectOne("APPLICATION_URL");

        if (env != null) {
            APPLICATION_URL = env.getEnv_txt();
        }

        // パスワード再設定画面のURLを生成
        // 生成されるURLはresetpassword/form/tokenの並びになります。
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(APPLICATION_URL);
        uriBuilder.pathSegment("resetpassword").queryParam("form").queryParam("token", token);
        String passwordResetUrl = uriBuilder.build().encode().toUriString();

        // メール本文を作成
        String text = user_id + "　様\n" + "お世話になっております。システム担当者です。\n\n" + "ご依頼いただきましたパスワード再設定画面のURLは下記の通りです。\n"
                + "リンクをクリックして、パスワード再設定画面からパスワードの再設定をお願いします。\n\n" + "【注意】URLの有効期間は約" + Constants.EXPIRYDATE_NXT
                + "分間です。有効期間を過ぎた場合は再手続きをお願いします。\n\n" + "---------------------------\n" + "パスワード再設定画面のURL:\n "
                + passwordResetUrl + "\n" + "\n---------------------------";

        // 送信元メールアドレスを取得
        Env sendFrom = envMapper.selectOne(Constants.MAIL_ENV.MAIL_ADMIN_CONTACT.name());
        if (sendFrom == null) {
            log.info("パスワード再設定用送信元メールアドレス取得失敗");
            String messageKey = "e.co.fw.3.001";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { Constants.MAIL_ENV.MAIL_ADMIN_CONTACT.name() }));
        }

        // パスワード再設定用メールタイトルを取得
        Env Subject = envMapper.selectOne(Constants.MAIL_ENV.MAIL_TITLE_PWREISSUE.name());
        if (Subject == null) {
            log.info("パスワード再設定用メールのタイトル取得失敗");
            String messageKey = "e.co.fw.3.001";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { Constants.MAIL_ENV.MAIL_TITLE_PWREISSUE.name() }));
        }

        // パスワード再設定画面のURLをメールで送信する。
        mailSendSharedService.mailsendregister(sendFrom.getEnv_txt(), user_id, Subject.getEnv_txt(), text);
    }
}
