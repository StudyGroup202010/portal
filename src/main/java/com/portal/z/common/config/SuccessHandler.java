package com.portal.z.common.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * ログイン成功時の処理<BR>
 *
 */
@Component("SuccessHandler")
@Slf4j
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserDetailsServiceImpl service;

    /**
     * ログインが成功した時の処理<BR>
     * 認証成功時におけるWeb層の処理(主に画面遷移に関する処理)を行うためのインタフェースなので、<BR>
     * 認証失敗回数のクリアなどのビジネスルールに依存する処理（ビジネスロジック）はここには実装しないようにしましょう。
     *
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        log.info("メソッド開始：");

        // ユーザー情報の取得
        AppUserDetails user = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String redirectPath = request.getContextPath();

        // パスワード更新日付のチェック
        if (user.getPass_update().after(new Date())) {
            // パスワード期限が切れてない
            redirectPath += "/home";

        } else {
            // パスワード期限切れ
            redirectPath += "/password/change";
        }

        log.info("メソッド終了：");

        // リダイレクト
        response.sendRedirect(redirectPath);
    }
}
