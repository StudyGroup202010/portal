package com.portal.z.home.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    /**
     * ホーム画面のGET用メソッド
     */
    @GetMapping("/home")
    public String getHome(Model model,Principal principal) {

        //コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/home :: home_contents");

        return "z/homeLayout";
    }

    /**
     * ホーム画面のログアウト用処理.
     */
    @PostMapping("/logout")
    public String postLogout() {

        //ログイン画面にリダイレクト
        return "redirect:/login";
    }

    //
    // 以下は本来は別のコントローラーに書くべきですが、参考としてここに書いています。
    //    
    /**
     * アドミン権限専用画面のGET用メソッド.
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
    @GetMapping("/admin")
    public String getAdmin(Model model) {

        //コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/admin :: admin_contents");

        //レイアウト用テンプレート
        return "z/homeLayout";
    }
}
