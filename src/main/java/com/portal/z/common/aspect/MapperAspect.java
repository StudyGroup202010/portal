package com.portal.z.common.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.portal.z.common.domain.model.AppUserDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP設定クラス（Mapper）
 */
@Aspect
@Component
@Slf4j
public class MapperAspect {

    /**
     * Mapperの実行前に共通項目(作成者・作成日時・更新者・更新日時)を設定する
     * 参考：https://qiita.com/kenhori/items/9941a159285f9360e877
     * 
     * @param jp ジョインポイント
     * @throws Throwable 例外
     */
    @Before("execution(* com.portal.z.common.domain.repository.*Mapper.insert*(..)) || "
            + "execution(* com.portal.z.common.domain.repository.*Mapper.update*(..))")
    public void setCommonProperty(JoinPoint jp) throws Throwable {

        // Mapperのメソッド名を取得
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        
        // AuthenticationSuccessEventListenerが、ログイン時に
        // ログイン失敗回数を0に更新する処理
        // userMapper.updateLoginMissTimes(userId)を
        // 実行しているが、この処理の対象外にしたい。
        // 暫定でメソッド名指定で処理対象外にする。
        // pointcut式で指定もできる模様。
        // →https://takeda-san.hatenablog.com/entry/2019/01/10/000044
        if (methodName.equals("updateLoginMissTimes")) {
            return;
        }
        
        // Mapperの1つめの引数を取得
        Object[] args = jp.getArgs();
        Object dto = args[0];

        // Spring Securityの認証情報を取得
        // ログインユーザー情報の取得
        
        // ログイン
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        // 現在日時を取得(Timestamp型)
        Timestamp now = new Timestamp(new Date().getTime());

        // insert*メソッドは作成者・作成日時・更新者・更新日時をセット
        if (methodName.startsWith("insert")) {
            setInsertProperty(user_auth.getUsername(), now, dto);
            setUpdateProperty(user_auth.getUsername(), now, dto);
            // update*メソッドは更新者・更新日時をセット
        } else if (methodName.startsWith("update")) {
            setUpdateProperty(user_auth.getUsername(), now, dto);
        }

    }

    // 作成者と作成日時をセット
    private void setInsertProperty(String userId, Timestamp now, Object dto) throws Throwable {
        try {
            // Mapperの引数にsetInsert_userメソッドがある場合、認証情報をセット
            Method setInsertUser = ReflectionUtils.findMethod(dto.getClass(), "setInsert_user", String.class);
            if (setInsertUser != null) {
                setInsertUser.invoke(dto, userId);
            }
            // Mapperの引数にsetInsert_dateメソッドがある場合、現在日時をセット
            Method setInsertDate = ReflectionUtils.findMethod(dto.getClass(), "setInsert_date", Timestamp.class);
            if (setInsertDate != null) {
                setInsertDate.invoke(dto, now);
            }
        } catch (Exception ex) {
            log.error("MapperAspectのsetInsertPropertyで例外が発生しました。");
            throw ex;
        }
    }

    // 更新者と更新日時をセット
    private void setUpdateProperty(String userId, Date now, Object dto) throws Throwable {

        try {

            // Mapperの引数にsetInsert_userメソッドがある場合、認証情報をセット
            Method setUpdateUser = ReflectionUtils.findMethod(dto.getClass(), "setUpdate_user", String.class);
            if (setUpdateUser != null) {
                setUpdateUser.invoke(dto, userId);
            }

            // Mapperの引数にsetUpdate_dateメソッドがある場合、現在日時をセット
            Method setUpdateDate = ReflectionUtils.findMethod(dto.getClass(), "setUpdate_date", Timestamp.class);
            if (setUpdateDate != null) {
                setUpdateDate.invoke(dto, now);
            }
        } catch (Exception ex) {
            log.error("MapperAspectのsetUpdatePropertyで例外が発生しました。");
            throw ex;
        }

    }

}
