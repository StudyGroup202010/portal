package com.portal.z.common.domain.service;

import javax.mail.MessagingException;

/**
 * メール送信用の共通クラス
 *
 */
public interface MailSendSharedService {

    /**
     * mailsendregister<BR>
     * 
     * @param sendFrom 送信者のアドレス
     * @param sendTo   送信先のアドレス
     * @param Subject  メールタイトル
     * @param text     メール本文
     * @return 送信成功：true<BR>
     *         送信失敗:false<BR>
     * @throws MessagingException 送信エラー
     */
    public boolean mailsendregister(String sendFrom, String sendTo, String Subject, String text)
            throws MessagingException;

}