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

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.util.MassageUtils;
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

    @Autowired
    private MassageUtils massageUtils;

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

        // ユーザIDに紐ついたメールアドレスを取得する。
        // ユーザー情報を取得
        User user = pwreissueService.selectOne_user(form.getUser_id());
        // 入力したユーザIDが存在しなかった場合、パスワード再設定画面に戻る
        if (user == null) {
            model.addAttribute("result1",
                    massageUtils.getMsg("e.co.fw.2.004", new String[] { "ユーザID " + form.getUser_id() }));
            return getPwreissue(form, model);
        }
        // 社員マスタ情報を取得
        Employee employee = pwreissueService.selectOne_employee(user.getEmployee_id());
        // 社員情報が存在しなかった場合、パスワード再設定画面に戻る
        if (employee == null) {
            model.addAttribute("result1",
                    massageUtils.getMsg("e.co.fw.2.004", new String[] { "ユーザID " + form.getUser_id() + " の社員情報" }));
            return getPwreissue(form, model);
        }
        // メール情報が存在しなかった場合、パスワード再設定画面に戻る
        if (employee.getMail() == null || employee.getMail().isEmpty() || employee.getMail().isBlank()) {
            model.addAttribute("result1",
                    massageUtils.getMsg("e.co.fw.2.004", new String[] { "ユーザID " + form.getUser_id() + " のメールアドレス" }));
            return getPwreissue(form, model);
        }

        try {
            // パスワード再発行情報を登録する。
            String result = pwreissueService.insertPwreissueinfo(form.getUser_id(), employee.getMail());
            model.addAttribute("result1", massageUtils.getMsg("i.co.pr.0.001", null));
            model.addAttribute("result2", massageUtils.getMsg("i.co.pr.0.002", null));
            model.addAttribute("result3", "仮パスワード：" + result);

        } catch (ApplicationException e) {
            model.addAttribute("result1", e.getMessage());

        } catch (MailConnectException | AuthenticationFailedException e) {
            model.addAttribute("result1", massageUtils.getMsg("e.co.fw.3.005", null) + e.getMessage());
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
