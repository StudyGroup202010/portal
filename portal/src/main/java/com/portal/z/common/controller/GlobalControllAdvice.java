package com.portal.z.common.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portal.z.common.exception.EnvEmptyResultDataAccessException;

@ControllerAdvice
@Component
public class GlobalControllAdvice {

    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：GlobalControllAdvice");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "DataAccessExceptionが発生しました。詳しくはログを参照してください");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
    
   /**
    * 環境マスタ取得エラー発生時の処理メソッド.
    */
    @ExceptionHandler(EnvEmptyResultDataAccessException.class)
    public String exceptionHandler(EnvEmptyResultDataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：GlobalControllAdvice");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", e);

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

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
