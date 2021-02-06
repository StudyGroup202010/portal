package com.portal.z.common.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * AOP設定
 *
 */
@Aspect
@Component
public class ErrorAspect {

    /**
     * 全てのクラスを対象にログを出力する。
     * 
     * @param ex エラー内容
     */
    @AfterThrowing(value = "execution(* *..*..*(..))"
            + " && (bean(*Controller) || bean(*Service) || bean(*Repository))", throwing = "ex")
    public void throwingNull(DataAccessException ex) {

        // 例外処理の内容（ログ出力）
        System.out.println("===========================================");
        System.out.println("DataAccessExceptionが発生しました。 : " + ex);
        System.out.println("===========================================");

    }
}
