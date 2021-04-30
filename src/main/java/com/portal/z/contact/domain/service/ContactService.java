package com.portal.z.contact.domain.service;

import javax.mail.MessagingException;

/**
 * ContactService
 *
 */
public interface ContactService {

    /**
     * 問い合わせ内容メール送信<BR>
     * 
     * @param sendFrom 問い合わせした人のアドレス
     * @param text     問い合わせ内容本文
     * @throws MessagingException 送信エラー
     */
    public void contactmailsendregister(String sendFrom, String text) throws MessagingException;
}
