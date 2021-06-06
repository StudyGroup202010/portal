package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 認証成功時のイベントリスナ
 *
 */
@Component
@Slf4j
public class AuthenticationSuccessEventListener {

    @Autowired
    UserMapper userMapper;

    /**
     * InteractiveAuthenticationSuccessEvent のイベント処理 <br>
     * 
     * すべての認証に成功した直後に動作します。<br> ログイン失敗回数に０をセットします。<br>
     * AuthenticationSuccessEvent→InteractiveAuthenticationSuccessEvent に変更<br>
     * 参考：https://terasolunaorg.github.io/guideline/5.1.0.RELEASE/ja/Security/
     * Authentication.html#id15 <br>
     * 
     * @param event 認証処理がすべて成功したことを通知するためのイベントクラス
     */
    @EventListener
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        log.info("正常なログインです。ログイン失敗回数を0にします。");

        // ログイン失敗回数を0に更新する。
        userMapper.updateLoginMissTimes(new User());
    }
}
