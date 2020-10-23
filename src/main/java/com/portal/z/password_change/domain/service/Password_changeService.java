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
import com.portal.z.common.domain.repository.UserMapper;
import com.portal.z.common.domain.service.EnvService;

@Transactional
@Service
public class Password_changeService {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    private EnvService envService;
    
    //パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;
    
    /**
     * パスワードを更新する.
     * @throws ParseException
     */
    public void updatePasswordDate(String userId, String password) throws ParseException {
        // パスワード暗号化
        String encryptPass = passwordEncoder.encode(password);

        // パスワード有効期限
        // 環境マスタに登録したパスワード有効期限月数を取得
        Env env = envService.selectOne("PASS_UPDATE_NXT");
        
        try {
            // 取得した値が数値かどうかを確認する
        	Integer.parseInt(env.getEnv_txt());
        } catch (NumberFormatException | NullPointerException e) {
        	throw new NumberFormatException("環境マスタPASS_UPDATE_NXTには月数を設定してください");
        }
     
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, Integer.parseInt(env.getEnv_txt()));  //月加算
        Date passwordUpdateDate = cal.getTime();
        
        //Userインスタンスの生成
        User user = new User();

        //フォームクラスをUserクラスに変換
        user.setUser_id(userId);                 //ユーザーID
        user.setPassword(encryptPass);           //パスワード
        user.setPass_update(passwordUpdateDate); //パスワード有効期限
        user.setUpdate_user(userId);             //更新者はログインしようとしているユーザ
    	
        // パスワード更新
        userMapper.updatePassupdate(user);
        //ToDo　更新が空振りしたときの処理
        //
        //
    }
}
