package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.a.common.domain.model.Env;
import com.portal.z.common.domain.model.User;
import com.portal.a.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.repository.UserMapper;
import com.portal.z.common.domain.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * ログイン認証失敗時の処理
 *
 */
@Component
@Slf4j
public class BadCredentialsEventListener {

    @Autowired
    private UserDetailsServiceImpl userdetailsService;

    @Autowired
    private EnvMapper envMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * AuthenticationFailureBadCredentialsEventのイベント処理 <br>
     * 
     * ログイン時にIDとパスワードの認証に失敗した直後に動作します。 <br>
     * ・ログイン失敗回数をカウントアップします。<BR>
     * ・ログイン失敗回数が環境マスタの「LOGIN_MISS_TIMES_MAX」の値以上になったら、ロック状態をtrueにします。
     * 
     * @param event AuthenticationFailureBadCredentialsEvent
     */
    @EventListener
    public void onBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {

        // ユーザーIDの取得(ログ用)
        String userId = event.getAuthentication().getName();

        // 存在しないユーザ名でのログイン失敗
        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            // ユーザＩＤが存在しない場合はユーザマスタを更新できないので終了
            log.error("メソッド終了：onBadCredentialsEvent（ユーザＩＤ {} が未存在）", userId);
            return;
        }

        // 存在するユーザ名でのログイン失敗
        log.error("メソッド終了：onBadCredentialsEvent（ユーザＩＤ {} が存在するがパスワード失敗）", userId);
        // ユーザー情報の取得
        AppUserDetails user = (AppUserDetails) userdetailsService.loadUserByUsername(userId);
        // ログイン失敗回数を1増やす
        int loginMissTime = user.getLogin_miss_times() + 1;
        // 失敗回数を更新する
        updateUnlock(userId, loginMissTime);
    }

    /**
     * 失敗回数とロック状態を更新する.<BR>
     * 
     * ログインに失敗したら、ユーザ情報の失敗回数をカウントアップする。<BR>
     * 失敗回数が環境マスタの「LOGIN_MISS_TIMES_MAX」の値以上になったら、ロック状態をtrueにする。
     * 
     * 
     * @param userId        ログインユーザID
     * @param loginMissTime ログイン失敗回数
     * @return ユーザ情報を更新
     */
    private boolean updateUnlock(String userId, int loginMissTime) {

        boolean lock = false; // ロック状態(無効)
        int LOGIN_MISS_LIMIT = 0; // ログイン失敗回数の最大値の初期値

        // 環境マスタに登録したログイン失敗回数の最大値を取得
        // 本来ならここでselectIntOneを使いたいところだが、例外がキャッチできない。（何か制約がある？）
        // なのでselectOneを使い、ここで値の評価もする事にした。
        Env env = envMapper.selectOne("LOGIN_MISS_TIMES_MAX");

        if (env != null) {
            try {
                // 取得した値をセットする。
                LOGIN_MISS_LIMIT = Integer.parseInt(env.getEnv_txt());

            } catch (NumberFormatException e) {
                log.error("環境マスタの「LOGIN_MISS_TIMES_MAX」に数字以外が登録されています");
                LOGIN_MISS_LIMIT = 0;
            }
        }

        if (loginMissTime >= LOGIN_MISS_LIMIT) {
            log.info("ログイン失敗回数の最大値に達したので {} をロックします", userId);
            lock = true;
        }

        // Userインスタンスの生成
        User user = new User();

        // Userクラスに値をセット
        user.setUser_id(userId); // ユーザーID
        user.setLogin_miss_times(loginMissTime); // ログイン失敗回数
        user.setLock_flg(lock); // ロック状態
        user.setUpdate_user(userId); // 更新者はログインしようとしているユーザ

        // パスワード更新
        // 更新実行
        return userMapper.updateLockflg(user);

    }
}
