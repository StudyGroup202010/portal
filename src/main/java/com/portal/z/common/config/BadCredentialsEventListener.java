package com.portal.z.common.config;

// ログイン失敗時の処理

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.service.EnvService;
import com.portal.z.common.domain.service.UserDetailsServiceImpl;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BadCredentialsEventListener {

    @Autowired
    private UserDetailsServiceImpl userdetailsService;

    @Autowired
    private EnvService envService;

    @Autowired
    private UserService userService;

    // 引数にセットしたイベントが発生したときにメソッドが呼ばれる
    @EventListener
    public void onBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {

        // ユーザーIDの取得
        String userId = event.getAuthentication().getName();

        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            // 存在しないユーザ名でのログイン失敗
            // ユーザＩＤが存在しない場合はユーザマスタを更新できないので終了
            log.info("メソッド終了：onBadCredentialsEvent（ユーザＩＤ " + userId + " が未存在）");
            return;
        } else {
            // 存在するユーザ名でのログイン失敗

            // ユーザー情報の取得
            AppUserDetails user = (AppUserDetails) userdetailsService.loadUserByUsername(userId);

            // ログイン失敗回数を1増やす
            int loginMissTime = user.getLogin_miss_times() + 1;

            // 失敗回数を更新する
            updateUnlock(userId, loginMissTime);
        }
    }

    //
    // 失敗回数と有効/無効フラグを更新する.
    //
    private boolean updateUnlock(String userId, int loginMissTime) {

        boolean lock = false; // ロックフラグ(無効)
        int LOGIN_MISS_LIMIT = 0; // ログイン失敗回数の最大値の初期値

        // 環境マスタに登録したログイン失敗回数の最大値を取得
        // 本来ならここでselectIntOneを使いたいところだが、例外がキャッチできない。（何か制約がある？）
        // なのでselectOneを使い、ここで値の評価もする事にした。
        Env env = envService.selectOne("LOGIN_MISS_TIMES_MAX");

        if (env != null) {
            try {
                // 取得した値をセットする。
                LOGIN_MISS_LIMIT = Integer.parseInt(env.getEnv_txt());

            } catch (NumberFormatException e) {
                log.info("環境マスタの「LOGIN_MISS_TIMES_MAX」に数字以外が登録されています");
                LOGIN_MISS_LIMIT = 0;
            }
        }

        log.info("LOGIN_MISS_LIMIT：" + LOGIN_MISS_LIMIT);

        if (loginMissTime >= LOGIN_MISS_LIMIT) {
            log.info("ログイン失敗回数の最大値に達したので " + userId + " をロックします");
            lock = true;
        }

        // Userインスタンスの生成
        User user = new User();

        // フォームクラスをUserクラスに変換
        user.setUser_id(userId); // ユーザーID
        user.setLogin_miss_times(loginMissTime); // ログイン失敗回数
        user.setLock_flg(lock); // ロック状態
        user.setUpdate_user(userId); // 更新者はログインしようとしているユーザ

        // パスワード更新
        // 更新実行
        boolean result = userService.updateLockflg(user);

        return result;

    }
}
