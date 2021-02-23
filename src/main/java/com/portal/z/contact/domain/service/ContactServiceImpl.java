package com.portal.z.contact.domain.service;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.service.MailSendSharedService;
import com.portal.z.common.domain.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * ContactServiceImpl
 *
 */
@Transactional
@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    MailSendSharedService mailSendSharedService;

    @Autowired
    private EnvMapper envMapper;

    /**
     * メール送信用メソッド<BR>
     * 
     * @param sendFrom 送信者のアドレス
     * @param text     メール本文
     * @return 送信成功：true<BR>
     *         送信失敗:false<BR>
     * @throws MessagingException 送信エラー
     */
    public boolean Contactmailsendregister(String sendFrom, String text) throws MessagingException {

        // 送信元メールアドレスを取得
        Env sendTo = envMapper.selectOne(Constants.MAIL_ENV.MAIL_ADMIN_CONTACT.name());
        if (sendTo == null) {
            log.info("問い合わせ用送信先メールアドレス取得失敗");
            return false;
        }

        // 問い合わせメールタイトルを取得
        Env Subject = envMapper.selectOne(Constants.MAIL_ENV.MAIL_TITLE_CONTACT.name());
        if (Subject == null) {
            log.info("問い合わせ用メールタイトル取得失敗");
            return false;
        }

        boolean result = mailSendSharedService.mailsendregister(sendFrom, sendTo.getEnv_txt(), Subject.getEnv_txt(),
                text);

        return result;
    }
}
