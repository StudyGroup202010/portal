package com.portal.z.pwreissue.domain.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.repository.PwreissueinfoMapper;
import com.portal.z.common.domain.service.EnvSharedService;
import com.portal.z.common.domain.service.MailSendSharedService;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.exception.ApplicationException;
import com.portal.z.common.exception.HttpErrorsImpl;

/**
 * パスワード再設定画面用のService
 *
 */
@Transactional
@Service
public class PwreissueServiceImpl implements PwreissueService {

    @Autowired
    private PwreissueinfoMapper pwreissueinfoMapper;

    @Autowired
    private EnvSharedService envSharedService;
    
    @Autowired
    MailSendSharedService mailSendSharedService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    public String insertPwreissueinfo(String user_id) {

        // トークンを生成
        String token = UUID.randomUUID().toString();

        // 仮パスワード条件設定
        List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));

        // 仮パスワード生成
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String rowSecret = passwordGenerator.generatePassword(10, rules);

        // 仮パスワード暗号化
        String encodeSecret = passwordEncoder.encode(rowSecret);

        // 仮パスワード有効期限の初期値を設定
        int PASS_UPDATE_NXT = Constants.PASS_UPDATE_NXT; // パスワード有効期限月数

        // 環境マスタに登録したパスワード有効期限月数を取得
        Integer env = envSharedService.selectIntOne("PASS_UPDATE_NXT");

        if (env != null) {
            PASS_UPDATE_NXT = env;
        }

        // 仮パスワード有効期限を計算
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, PASS_UPDATE_NXT); // 月加算
        Date passwordUpdateDate = cal.getTime();

        // パスワード再発行情報インスタンスの生成
        Pwreissueinfo pwreissueinfo = new Pwreissueinfo();

        // パスワード再発行情報クラスに設定
        pwreissueinfo.setToken(token);
        pwreissueinfo.setUser_id(user_id);
        pwreissueinfo.setSecret(encodeSecret);
        pwreissueinfo.setExpirydate(passwordUpdateDate);

        try {
            // パスワード再発行情報に追加
            pwreissueinfoMapper.insertOne(pwreissueinfo);

            return rowSecret;

        } catch (DataIntegrityViolationException  e) {
            // 参照整合性エラーが発生した時はビジネス例外として返す。
            throw new ApplicationException(HttpErrorsImpl.DATAINTEGRITY, e, user_id);
        }
    }
    
    public void sendMailToUser(String user_id) throws MessagingException {
        // パスワード再設定用情報を検索する。
        Pwreissueinfo pwreissueinfo = pwreissueinfoMapper.selectOneByUserid(user_id);

        // パスワード再設定画面のURLを生成
        // 生成されるURLはresetpassword/form/tokenの並びになります。
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/");
        uriBuilder.pathSegment("resetpassword").queryParam("form").queryParam("token", pwreissueinfo.getToken());
        String passwordResetUrl = uriBuilder.build().encode().toUriString();

        System.out.println("test:" + passwordResetUrl);

        // パスワード再設定画面のURLをメールで送信する。
        mailSendSharedService.mailsendregister(user_id, user_id, "URLを送ります", passwordResetUrl);

    }
}
