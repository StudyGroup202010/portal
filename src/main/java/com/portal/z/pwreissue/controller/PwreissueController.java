package com.portal.z.pwreissue.controller;

import java.text.ParseException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.z.common.exception.ApplicationException;
import com.portal.z.pwreissue.domain.model.PwreissueForm;
import com.portal.z.pwreissue.domain.service.PwreissueService;
import com.sun.mail.util.MailConnectException;

/**
 * パスワード再設定画面用Controller
 *
 */
@Controller
public class PwreissueController {

    @Autowired
    PwreissueService pwreissueService;

    /**
     * 画面表示.
     * 
     * @param form  form
     * @param model model
     * @return z/createReissueInfoForm
     */
    @GetMapping("/pwreissue")
    public String getPwreissue(@ModelAttribute PwreissueForm form, Model model) {
        return "z/pwreissue";
    }

    /**
     * パスワード再発行情報登録
     * 
     * @param model         model
     * @param form          form
     * @param bindingResult bindingResult
     * @return パスワード再設定画面
     * @throws ParseException     ParseException
     * @throws MessagingException MessagingException
     */
    @PostMapping(value = "/pwreissue", params = "regist")
    public String postPwreissue(Model model, @ModelAttribute @Validated PwreissueForm form, BindingResult bindingResult)
            throws ParseException, MessagingException {

        // 入力チェックに引っかかった場合、パスワード再設定画面に戻る
        if (bindingResult.hasErrors()) {
            return getPwreissue(form, model);
        }

        try {
            // パスワード再発行情報を登録する。
            String result = pwreissueService.insertPwreissueinfo(form.getUser_id());

            model.addAttribute("result1", "パスワード再設定用のURLを、ご入力いただいたメールアドレスに送信しました。");
            model.addAttribute("result2", "メール本文に記載されているURLにアクセスし、以下の仮パスワードを使ってログインしてください。");
            model.addAttribute("result3", "仮パスワード：" + result);

        } catch (ApplicationException e) {
            if (e.getMessage_id().toString().compareTo("e.co.fw.3.004") == 0) {
                // 整合性制約エラーの時
                model.addAttribute("result1", "このユーザIDは登録されていません。");
            } else {
                model.addAttribute("result1", "送信が出来ませんでした。送信不可になっているか、送信設定が間違っている可能性があります。" + e.getMessage());
            }
        } catch (MailConnectException | AuthenticationFailedException e) {
            model.addAttribute("result1", "送信が出来ませんでした。送信設定が間違っている可能性があります。" + e.getMessage());
        }

        return getPwreissue(form, model);
    }
    
    /**
     * 戻る処理.<BR>
     * 
     * ログイン画面に戻る。
     * 
     * @param model モデル
     * @return getUserList(model)
     */
    @PostMapping(value = "/pwreissue", params = "back")
    public String postPwreissueback(Model model) {
        // ログイン画面を表示
        return "z/login";
    }
}
