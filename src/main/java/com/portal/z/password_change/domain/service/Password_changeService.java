package com.portal.z.password_change.domain.service;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.EnvService;
import com.portal.z.common.domain.service.UserService;
import com.portal.z.common.domain.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * パスワード変更画面のService
 *
 */
@Transactional
@Service
@Slf4j
public class Password_changeService {

    @Autowired
    private UserService userService;

    @Autowired
    private EnvService envService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * パスワードを更新する.
     * 
     * @param userId   userId
     * @param password password
     * @throws ParseException ParseException
     */
    public void updatePasswordDate(String userId, String password) throws ParseException {
        // パスワード暗号化
        String encryptPass = passwordEncoder.encode(password);

        int PASS_UPDATE_NXT = Constants.PASS_UPDATE_NXT; // パスワード有効期限月数

        // パスワード有効期限
        // 環境マスタに登録したパスワード有効期限月数を取得
        Env env = envService.selectIntOne("PASS_UPDATE_NXT");

        if (env != null) {
            PASS_UPDATE_NXT = Integer.parseInt(env.getEnv_txt());
        }

        log.info("PASS_UPDATE_NXT：" + PASS_UPDATE_NXT);

        // パスワード有効期限を計算
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, PASS_UPDATE_NXT); // 月加算
        Date passwordUpdateDate = cal.getTime();

        // Userインスタンスの生成
        User user = new User();

        // フォームクラスをUserクラスに変換
        user.setUser_id(userId); // ユーザーID
        user.setPassword(encryptPass); // パスワード
        user.setPass_update(passwordUpdateDate); // パスワード有効期限
        user.setUpdate_user(userId); // 更新者はログインしようとしているユーザ

        // パスワード更新
        userService.updatePassupdate(user);

    }
}
