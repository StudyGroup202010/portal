package com.portal.z.home.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.DateUtils;

/**
 * home用のController
 *
 */
@Controller
public class HomeController {

    /**
     * ホーム画面のGET用メソッド
     * 
     * @param model     model
     * @param principal principal
     * @return z/homeLayout
     */
    @GetMapping("/home")
    public String getHome(Model model, Principal principal) {

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/home :: home_contents");

        // 本日日付を取得
        model.addAttribute("today", DateUtils.getStringFromDateFormat2(LocalDate.now()));

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        model.addAttribute("userid", user_auth.getUsername());

        return "z/homeLayout";
    }

    /**
     * ホーム画面のログアウト用処理.
     * 
     * @return redirect:/login
     */
    @PostMapping("/logout")
    public String postLogout() {

        // ログイン画面にリダイレクト
        return "redirect:/login";
    }

    //
    // 以下は本来は別のコントローラーに書くべきですが、参考としてここに書いています。
    //
    /**
     * 一般権限専用画面のGET用メソッド.
     * 
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
    @GetMapping("/general")
    public String getGeneral(Model model) {

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/general :: general_contents");

        // レイアウト用テンプレート
        return "z/homeLayout";
    }

    /**
     * アドミン権限専用画面のGET用メソッド.
     * 
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
    @GetMapping("/admin")
    public String getAdmin(Model model) {

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/admin :: admin_contents");

        // レイアウト用テンプレート
        return "z/homeLayout";
    }
}
