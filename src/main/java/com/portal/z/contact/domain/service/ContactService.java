package com.portal.z.contact.domain.service;

import javax.mail.MessagingException;

/**
 * ContactService
 *
 */
public interface ContactService {

    /**
     * Contactmailsendregister<BR>
     * 
     * @param sendFrom 送信者のアドレス
     * @param text     メール本文
     * @return 送信成功：true<BR>
     *         送信失敗:false<BR>
     * @throws MessagingException 送信エラー
     */
    public boolean Contactmailsendregister(String sendFrom, String text) throws MessagingException;
}
