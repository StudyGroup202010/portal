package com.portal.z.common.config;

// ログイン失敗時の処理

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
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

        //ユーザー情報の取得
    	// 本来であればLoginUserRepositoryの例外（UsernameNotFoundException）を受け取って判断したいところ。
    	// しかし、Springの標準でBadCredentialsExceptionに変換されてしまう。
    	// 設定で変換させない方法もあるようだが、あまり資料無いし内部をいじりたくないので、再検索して判断することにした。
        User user_i = userService.selectOne(userId);
        
        // ユーザＩＤが存在しない場合はユーザマスタを更新できないので終了
        if (user_i == null ) {
        	log.info("メソッド終了：onBadCredentialsEvent（ユーザＩＤ " + userId + " が未存在）" );
        	return;
        }
        
        // ユーザー情報の取得
        AppUserDetails user = (AppUserDetails) userdetailsService.loadUserByUsername(userId);

        // ログイン失敗回数を1増やす
        int loginMissTime = user.getLogin_miss_times() + 1;
        
        // 失敗回数を更新する
        updateUnlock(userId,loginMissTime);
        
    }

    //
    //失敗回数と有効/無効フラグを更新する.
    //
    private boolean updateUnlock(String userId, int loginMissTime) {
    	
        boolean lock = false;      // ロックフラグ(無効)
        int LOGIN_MISS_LIMIT = 0;  // ログイン失敗回数の最大値

        // 環境マスタに登録したログイン失敗回数の最大値を取得
        Env env = envService.selectOne("LOGIN_MISS_TIMES_MAX");

        if (env != null ) {
        	  LOGIN_MISS_LIMIT = Integer.parseInt(env.getEnv_txt());
        }else {
        	log.info("環境マスタの「LOGIN_MISS_TIMES_MAX」が登録されていません。" );
        	log.info("初期値を登録します。" );
        	  LOGIN_MISS_LIMIT = 3;
        }

        if(loginMissTime >= LOGIN_MISS_LIMIT) {
            log.info("ログイン失敗回数の最大値に達したので " + userId + " をロックします");
            lock = true;
        }
    
        //Userインスタンスの生成
        User user = new User();

        //フォームクラスをUserクラスに変換
        user.setUser_id(userId);                 //ユーザーID
        user.setLogin_miss_times(loginMissTime); //ログイン失敗回数
        user.setLock_flg(lock);                  //ロック状態
        user.setUpdate_user(userId);             //更新者はログインしようとしているユーザ

        // パスワード更新
        //更新実行
        boolean result = userService.updateLockflg(user);

        return result;

    }
}
