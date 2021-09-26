package com.portal.z.contact.controller;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.exception.ApplicationException;
import com.portal.z.contact.domain.model.ContactForm;
import com.portal.z.contact.domain.service.ContactService;
import com.sun.mail.util.MailConnectException;

/**
 * Contact用のController
 *
 */
@Controller
public class ContactController {

    @Autowired
    ContactService contactService;
    
    @Autowired
    private MassageUtils massageUtils;

    /**
     * 問い合わせ画面のGETメソッド用処理.<BR>
     * 
     * 問い合わせ画面を表示する。
     * 
     * @param form  入力内容
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/contact")
    public String getContact(@ModelAttribute ContactForm form, Model model) {

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        form.setContact_name(user_auth.getUsername());  // ユーザID
        model.addAttribute("contactForm", form);

        // コンテンツ部分に問い合わせ画面を表示するための文字列を登録
        model.addAttribute("contents", "z/contact :: contact_contents");

        return "z/homeLayout";
    }

    /**
     * 問い合わせ画面のメール送信メソッド用処理.<BR>
     * 
     * @param form          フォーム
     * @param bindingResult 入力内容
     * @param model         モデル
     * @return 問い合わせ画面
     * @throws MessagingException メール送信エラー
     */
    @RequestMapping(value = "/contact", params = "sendmail")
    public String sendmail(@ModelAttribute @Validated ContactForm form, BindingResult bindingResult, Model model)
            throws MessagingException {

        // 入力チェックに引っかかった場合、問い合わせ画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、問い合わせ画面に戻ります
            return getContact(form, model);
        }

        // 受け取った内容からメール本文を作成
        String text = "お問い合わせは下記の通りです。\n\n" + "---------------------------\n" + "お名前: " + form.getContact_name() + "\n"
                + "メールアドレス: " + form.getContact_email() + "\n" + "メッセージ: \n" + form.getContact_message()
                + "\n---------------------------";

        try {
            // メールを送信する。
            contactService.contactmailsendregister(form.getContact_email(), text);
            model.addAttribute("result", massageUtils.getMsg("i.co.pr.0.003", null));
            // Modelを初期化
            form.setContact_name(null); // ユーザID
            form.setContact_email(null); // メールアドレス
            form.setContact_message(null); // お問い合わせ内容
            model.addAttribute("ContactForm", form);

        } catch (ApplicationException e) {
            model.addAttribute("result", e.getMessage());
        } catch (MailConnectException | AuthenticationFailedException e) {
            model.addAttribute("result", massageUtils.getMsg("e.co.fw.3.005", null) + e.getMessage());
        }

        // 問い合わせ画面を表示
        return getContact(form, model);
    }
}