package com.portal.z.contact.domain.service;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Env;
import com.portal.a.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.service.MailSendSharedService;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.exception.ApplicationException;

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

    @Autowired
    private MassageUtils massageUtils;

    public void contactmailsendregister(String sendFrom, String text) throws MessagingException {

        // 送信先メールアドレスを取得
        Env sendTo = envMapper.selectOne(Constants.MAIL_ENV.MAIL_ADMIN_CONTACT.name());
        if (sendTo == null) {
            log.error("問い合わせ用送信先メールアドレス取得失敗");
            String messageKey = "e.co.fw.3.001";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { Constants.MAIL_ENV.MAIL_ADMIN_CONTACT.name() }));
        }

        // 問い合わせメールタイトルを取得
        Env Subject = envMapper.selectOne(Constants.MAIL_ENV.MAIL_TITLE_CONTACT.name());
        if (Subject == null) {
            log.error("問い合わせ用メールタイトル取得失敗");
            String messageKey = "e.co.fw.3.001";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { Constants.MAIL_ENV.MAIL_TITLE_CONTACT.name() }));
        }

        mailSendSharedService.mailsendregister(sendFrom, sendTo.getEnv_txt(), Subject.getEnv_txt(), text);
    }
}
