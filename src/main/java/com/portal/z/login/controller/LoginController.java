package com.portal.z.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ログイン画面用のController
 *
 */
@Controller
public class LoginController {

    /**
     * ログイン画面のGETメソッド用処理.
     * 
     * @param model model
     * @return z/login
     */
    @GetMapping("/login")
    public String getLogin(Model model) {

        // login.htmlに画面遷移
        return "z/login";
    }

    /**
     * ログイン画面のPOSTメソッド用処理.
     * 
     * @param model model
     * @return redirect:/home
     */
    @PostMapping("/login")
    public String postLogin(Model model) {

        // ホーム画面に遷移
        return "redirect:/home";
    }

    /**
     * セッションタイムアウトになった時のGETメソッド用処理.
     * 
     * @param model model
     * @return z/invalidSession
     */
    @GetMapping("/error/session")
    public String getSessionError(Model model) {
        return "z/invalidSession";
    }
}