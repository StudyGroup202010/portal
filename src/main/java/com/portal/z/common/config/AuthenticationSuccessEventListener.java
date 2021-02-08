package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    UserService service;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

        log.info("正常なログインです。ログイン失敗回数を0にします。");

        // ユーザー情報の取得
        String userId = event.getAuthentication().getName();

        // ログイン失敗回数を0に更新する。
        service.updateLoginMissTimes(userId);
    }
}
