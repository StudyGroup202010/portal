package com.portal.z.contact.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.portal.z.common.domain.logic.MailSend;
import com.portal.z.contact.domain.model.ContactForm;

/**
 * Contact用のController
 *
 */
@Controller
public class ContactController {
    
    @Autowired
    MailSend mailsend;
    
    /**
     * 問い合わせ画面のGETメソッド用処理.<BR>
     * 
     * 問い合わせ画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/contact")
    public String getUserList(Model model) {

        // 入力項目のformを登録
        ContactForm form = new ContactForm();
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
    public String sendmail(@ModelAttribute ContactForm form, BindingResult bindingResult, Model model)
            throws MessagingException {

        // 受け取った内容からメール本文を作成
        String text = "お問い合わせは下記の通りです。\n\n" + "---------------------------\n" + "お名前: " + form.getName() + "\n"
                + "メールアドレス: " + form.getEmail() + "\n" + "メッセージ: \n" + form.getMessage()
                + "\n---------------------------";

        // メールを送信する。
        boolean result = mailsend.mailsendregister(form.getEmail(), "suzuki196906@gmail.com", "お問い合わせがありました", text);

        if (result == true) {
            model.addAttribute("result", "ご記入いただいた内容を管理者に送信しました。");
        } else {
            model.addAttribute("result", "送信が出来ませんでした。送信不可になっているか、設定が間違っている可能性があります。");
        }
        // 問い合わせ画面を表示
        return getUserList(model);
    }
}