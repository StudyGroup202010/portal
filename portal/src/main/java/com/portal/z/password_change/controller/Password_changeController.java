package com.portal.z.password_change.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.z.common.exception.EnvEmptyResultDataAccessException;
import com.portal.z.login.domain.model.AppUserDetails;
import com.portal.z.password_change.domain.model.PasswordForm;
import com.portal.z.password_change.domain.service.Password_changeService;

@Controller
public class Password_changeController {
  
    @Autowired
    private Password_changeService password_changeService;
    
    /**
     * 画面表示.
     */
    @GetMapping("/password/change")
    public String getPasswordChange(Model model,
            @ModelAttribute PasswordForm form) {
        return "z/password_change";
    }

    /**
     * パスワード変更.
     * @throws ParseException
     */
    @PostMapping("/password/change")
    public String postPasswordChange(Model model,
            @ModelAttribute PasswordForm form,
            @AuthenticationPrincipal AppUserDetails user) throws ParseException {
    	
        password_changeService.updatePasswordDate(user.getUser_id(), form.getPassword());

        return "redirect:/home";
    }
    
    /**
     * DataAccessException発生時の処理メソッド.
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "Password_changeControllerでDataAccessExceptionが発生しました");

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
        model.addAttribute("message", "Password_changeControllerでエラーが発生しました" + e);

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
    

    /**
     * Exception発生時の処理メソッド.
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        //model.addAttribute("message", "Password_changeControllerでExceptionが発生しました");
        model.addAttribute("message", e.getMessage());

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
    
}
