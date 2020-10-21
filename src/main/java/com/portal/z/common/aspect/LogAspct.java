package com.portal.z.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspct {

    /**
     * コントローラークラスのログ出力用アスペクト.
     */
	// JointPoint（実行タイミング）はAround（メソッド実行の前後）
	//　実行場所はwithin(Controllerアノテーションがついている全ての場所）
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        //System.out.println("メソッド開始： " + jp.getSignature());
        log.info("メソッド開始：" + jp.getSignature());

        try {
            //メソッド実行
            Object result = jp.proceed();

            //System.out.println("メソッド終了： " + jp.getSignature());
            log.info("メソッド終了：" + jp.getSignature());

            return result;

        } catch (Exception e) {
            //System.out.println("メソッド異常終了： " + jp.getSignature());
        	log.error("メソッド異常終了：" + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
