package com.portal.z.pwreissue.domain.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.model.User;

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
     * @param user_id パスワード再発行したいユーザID
     * @param mail_to メール送信する宛先メール
     * @return 生成された秘密情報
     * @throws MessagingException MessagingException
     */
    public String insertPwreissueinfo(String user_id, String mail_to) throws MessagingException;

    /**
     * ユーザマスタ１件取得用メソッド.
     * 
     * @param user_id user
     * @return User
     */
    public User selectOne_user(String user_id);

    /**
     * 社員マスタ１件取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return employee
     */
    public Employee selectOne_employee(String employee_id);
}
