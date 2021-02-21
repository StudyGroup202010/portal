package com.portal.z.contact.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * @param form  入力内容
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/contact")
    public String getContact(@ModelAttribute ContactForm form, Model model) {

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

        // メールを送信する。
        boolean result = mailsend.mailsendregister(form.getContact_email(), "suzuki196906@gmail.com", "お問い合わせがありました", text);

        if (result == true) {
            model.addAttribute("result", "ご記入いただいた内容を管理者に送信しました。");
            // Modelを初期化
            form.setContact_name(null); // ユーザID
            form.setContact_email(null); // メールアドレス
            form.setContact_message(null); // お問い合わせ内容
            model.addAttribute("ContactForm", form);
        } else {
            model.addAttribute("result", "送信が出来ませんでした。送信不可になっているか、設定が間違っている可能性があります。");
        }
        // 問い合わせ画面を表示
        return getContact(form, model);
    }
}