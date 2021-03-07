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
     * パスワード再発行情報に追加し、URLをメール送信する。
     * 
     * @param user_id user_id
     * @return 生成された秘密情報
     * @throws MessagingException MessagingException
     */
    public String insertPwreissueinfo(String user_id) throws MessagingException;
}
