package com.portal.z.pwreissue.domain.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PwreissueService
 *
 */
@Transactional
@Service
public interface PwreissueService {
    /**
     * パスワード再発行情報に追加する。
     * 
     * @param user_id user_id
     * @return 仮パスワード
     */
    public String insertPwreissueinfo(String user_id);

    /**
     * パスワード再設定画面のURLをメールで送る。
     * 
     * @param user_id user_id
     * @throws MessagingException
     */
    public void sendMailToUser(String user_id) throws MessagingException;

}
