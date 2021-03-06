package com.portal.z.password_change.domain.service;

import java.text.ParseException;

/**
 * Password_changeService
 *
 */
public interface Password_changeService {

    /**
     * パスワードを更新する.
     * 
     * @param userId   userId
     * @param password password
     * @throws ParseException ParseException
     */
    public void updatePasswordDate(String userId, String password) throws ParseException;
}
