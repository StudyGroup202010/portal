//package com.portal.z.common.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * AOP設定（ログ出力）
// *
// */
//@Aspect
//@Component
//@Slf4j
//public class LogAspct {
//
//    /**
//     * controller起動時にログを出力する。<BR>
//     * JointPoint（実行タイミング）はAround（メソッド実行の前後）<BR>
//     * 実行場所はwithin(Controllerアノテーションがついている全ての場所）
//     * 
//     * @param jp ProceedingJoinPoint
//     * @return 実行結果
//     * @throws Throwable Throwable
//     */
//    @Around("@within(org.springframework.stereotype.Controller)")
//    public Object startLog_controller(ProceedingJoinPoint jp) throws Throwable {
//
//        // System.out.println("メソッド開始： " + jp.getSignature());
//        log.info("メソッド開始：{}", jp.getSignature());
//
//        try {
//            // メソッド実行
//            Object result = jp.proceed();
//
//            // System.out.println("メソッド終了： " + jp.getSignature());
//            log.info("メソッド終了：{}", jp.getSignature());
//
//            return result;
//
//        } catch (Exception e) {
//            // System.out.println("メソッド異常終了： " + jp.getSignature());
//            log.error("メソッド異常終了：{}", jp.getSignature());
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    /**
//     * service起動時にログを出力する。<BR>
//     * JointPoint（実行タイミング）はAround（メソッド実行の前後）<BR>
//     * 実行場所はwithin(Serviceアノテーションがついている全ての場所）
//     * 
//     * @param jp ProceedingJoinPoint
//     * @return 実行結果
//     * @throws Throwable Throwable
//     */
//    @Around("@within(org.springframework.stereotype.Service)")
//    public Object startLog_service(ProceedingJoinPoint jp) throws Throwable {
//
//        log.info("Sメソッド開始：{}", jp.getSignature());
//
//        try {
//            // メソッド実行
//            Object result = jp.proceed();
//
//            log.info("Sメソッド終了：{}", jp.getSignature());
//
//            return result;
//
//        } catch (Exception e) {
//            log.error("Sメソッド異常終了：{}", jp.getSignature());
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    /**
//     * Component起動時にログを出力する。<BR>
//     * JointPoint（実行タイミング）はAround（メソッド実行の前後）<BR>
//     * 実行場所はwithin(Componentアノテーションがついている全ての場所）
//     * 
//     * @param jp ProceedingJoinPoint
//     * @return 実行結果
//     * @throws Throwable Throwable
//     */
//    @Around("@within(org.springframework.stereotype.Component)")
//    public Object startLog_component(ProceedingJoinPoint jp) throws Throwable {
//
//        log.info("Cメソッド開始：{}", jp.getSignature());
//
//        try {
//            // メソッド実行
//            Object result = jp.proceed();
//
//            log.info("Cメソッド終了：{}", jp.getSignature());
//
//            return result;
//
//        } catch (Exception e) {
//            log.error("Cメソッド異常終了：{}", jp.getSignature());
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    /**
//     * repository起動時にログを出力する。<BR>
//     * 
//     * JointPoint（実行タイミング）はAround（メソッド実行の前後）<BR>
//     * 実行場所はwithin(Repositoryアノテーションがついている全ての場所）
//     * 
//     * @param jp ProceedingJoinPoint
//     * @return 実行結果
//     * @throws Throwable Throwable
//     */
//    @Around("@within(org.springframework.stereotype.Repository)")
//    public Object startLog_repository(ProceedingJoinPoint jp) throws Throwable {
//
//        log.info("Rメソッド開始：{}", jp.getSignature());
//
//        try {
//            // メソッド実行
//            Object result = jp.proceed();
//
//            log.info("Rメソッド終了：{}", jp.getSignature());
//
//            return result;
//
//        } catch (Exception e) {
//            log.error("Rメソッド異常終了：{}", jp.getSignature());
//            e.printStackTrace();
//            throw e;
//        }
//    }
//}
