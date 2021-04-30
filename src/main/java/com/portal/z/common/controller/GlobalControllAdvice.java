package com.portal.z.common.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.portal.z.common.exception.ApplicationException;

/**
 * 共通エラー処理
 *
 */
@ControllerAdvice
@Component
public class GlobalControllAdvice {

    /**
     * ApplicationExceptionが発生した時の処理
     * 
     * @param e     ApplicationException
     * @param model モデル
     * @return error
     */
    @ExceptionHandler(ApplicationException.class)
    public String dataAccessExceptionHandler(ApplicationException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（AP）：GlobalControllAdvice");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "ApplicationExceptionが発生しました。詳しくはログを参照してください。" + e.getMessage());

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /**
     * DataAccessExceptionが発生したときの処理
     * 
     * @param e     DataAccessException
     * @param model モデル
     * @return error
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：GlobalControllAdvice");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "DataAccessExceptionが発生しました。詳しくはログを参照してください。" + e);

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /**
     * Exceptionが発生したときの処理
     * 
     * @param e     Exception
     * @param model モデル
     * @return error
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：GlobalControllAdvice");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", e);

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}
