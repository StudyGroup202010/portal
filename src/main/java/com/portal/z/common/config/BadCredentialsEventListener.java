package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.EnvService;
import com.portal.z.common.domain.service.UserDetailsServiceImpl;
import com.portal.z.common.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * ログイン失敗時の処理
 *
 */
@Component
@Slf4j
public class BadCredentialsEventListener {

    @Autowired
    private UserDetailsServiceImpl userdetailsService;

    @Autowired
    private EnvService envService;

    @Autowired
    private UserService userService;

    /**
     * 認証に失敗したときの処理<BR>
     * 認証に失敗したときはログイン失敗回数をカウントアップする。<BR>
     * 引数にセットしたイベントが発生したときにメソッドが呼ばれる
     * 
     * @param event AuthenticationFailureBadCredentialsEvent
     */
    @EventListener
    public void onBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {

        // ユーザーIDの取得 //TODO log出力のためです。ログが不要になったら見直します。
        String userId = event.getAuthentication().getName();

        // 存在しないユーザ名でのログイン失敗
        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            // ユーザＩＤが存在しない場合はユーザマスタを更新できないので終了
            log.info("メソッド終了：onBadCredentialsEvent（ユーザＩＤ " + userId + " が未存在）");
            return;

        } else {
            // 存在するユーザ名でのログイン失敗
            log.info("メソッド終了：onBadCredentialsEvent（ユーザＩＤ " + userId + " が存在するがパスワード失敗）");
            // ユーザー情報の取得
            AppUserDetails user = (AppUserDetails) userdetailsService.loadUserByUsername(userId);
            // ログイン失敗回数を1増やす
            int loginMissTime = user.getLogin_miss_times() + 1;
            // 失敗回数を更新する
            updateUnlock(userId, loginMissTime);

        }
    }

    /**
     * 失敗回数と有効/無効フラグを更新する.<BR>
     * 
     * ログインに失敗したら、ユーザ情報の失敗回数をカウントアップする。<BR>
     * 失敗回数が環境マスタの「LOGIN_MISS_TIMES_MAX」の値以上になったら、有効／無効フラグを「無効」にする。
     * 
     * 
     * @param userId        ログインユーザID
     * @param loginMissTime ログイン失敗回数
     * @return ユーザ情報を更新
     */
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
