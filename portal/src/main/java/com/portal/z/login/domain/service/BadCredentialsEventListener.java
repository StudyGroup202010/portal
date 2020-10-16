package com.portal.z.login.domain.service;

// ログイン失敗時の処理
// ApplicationEventの処理なので遷移先の変更はできない

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.service.EnvService;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.UserService;

import com.portal.z.login.domain.model.AppUserDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BadCredentialsEventListener {

    @Autowired
    private UserDetailsServiceImpl service;
    
    @Autowired
    private EnvService envService;
    
    @Autowired
    private UserService userService;

    // 引数にセットしたイベントが発生したときにメソッドが呼ばれる
    @EventListener
    public void onBadCredentialsEvent(AuthenticationFailureBadCredentialsEvent event) {

    	log.info("メソッド開始：" );
    	
        // 存在しないユーザ名の場合はユーザマスタを更新できないので無視
        if (event.getException().getClass().equals(UsernameNotFoundException.class)) {
            log.info("ユーザーが存在しません。");
            return;
        }

    	// ユーザーIDの取得
        String userId = event.getAuthentication().getName();

        // ユーザー情報の取得
        AppUserDetails user = (AppUserDetails) service.loadUserByUsername(userId);

        // ログイン失敗回数を1増やす
        int loginMissTime = user.getLogin_miss_times() + 1;
        
        // 失敗回数を更新する
        updateUnlock(userId,loginMissTime);
        
        log.info("メソッド終了：" );
    }

    //
    //失敗回数と有効/無効フラグを更新する.
    //
    private boolean updateUnlock(String userId, int loginMissTime) {
    	
    	log.info("メソッド開始：" );

        // ロックフラグ(無効)
        boolean lock = false;
    
        // 環境マスタに登録したログイン失敗の上限回数を取得
        //　ToDo 取得出来なかったときの処理を追加
        Env env = envService.selectOne("LOGIN_MISS_TIMES_MAX");
        final int LOGIN_MISS_LIMIT = Integer.parseInt(env.getEnv_txt());
   
        if(loginMissTime >= LOGIN_MISS_LIMIT) {
            lock = true;
            log.info(userId + "をロックします");
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
        
        log.info("メソッド終了：" );
        
        return result;
    }
}
