package com.portal.z.resetpassword.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Pwreissueinfo;

/**
 * PwreissueService
 *
 */
@Transactional
@Service
public interface ResetpasswordService {
    
    /**
     * パスワード再発行情報を検索する。
     * 
     * @param token token
     * @return Pwreissueinfo
     */
    public Pwreissueinfo selectOne(String token);

    /**
     * 仮パスワードと秘密情報の整合性チェック<BR>
     * 
     * 入力されたtokenとsecretが、パスワード再発行情報の内容と同じかどうかを確認する。
     * 
     * @param token  token
     * @param secret secret
     * @return true/false
     */
    public boolean checksecret(String token, String secret);

    /**
     * パスワード再発行情報を削除する。
     * 
     * @param user_id user_id
     * @return true/false
     */
    public boolean deletePwreissueinfoByUserid(String user_id);
}
