package com.portal.z.resetpassword.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.repository.PwreissueinfoMapper;
import com.portal.z.common.domain.repository.UserMapper;

/**
 * パスワード再発行画面用のService
 *
 */
@Transactional
@Service
public class ResetpasswordServiceImpl implements ResetpasswordService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PwreissueinfoMapper pwreissueinfoMapper;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    public Pwreissueinfo selectOne(String token) {
        // パスワード再発行情報を検索する。
        return pwreissueinfoMapper.selectOne(token);
    }

    public boolean checksecret(String token, String secret) {
        // パスワード再発行情報を検索する。
        Pwreissueinfo pwreissueinfo = pwreissueinfoMapper.selectOne(token);
        if (pwreissueinfo == null) {
            // パスワード再発行情報が検索されなかった。
            return false;
        }

        if (passwordEncoder.matches(secret, pwreissueinfo.getSecret())) {
            // 仮パスワードと秘密情報が整合した。
            return true;
        }
        return false;
    }

    public boolean deletePwreissueinfoByUserid(String user_id) {
        return pwreissueinfoMapper.deleteOneByUserid(user_id);
    }
}
