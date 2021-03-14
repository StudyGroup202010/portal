package com.portal.z.password_change.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.service.UserSharedService;
import com.portal.z.password_change.domain.model.PasswordForm;

/**
 * パスワード変更画面用Controller
 *
 */
@Controller
public class Password_changeController {

    @Autowired
    private UserSharedService userSharedService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 画面表示.
     * 
     * @param model model
     * @param form  form
     * @return z/password_change
     */
    @GetMapping("/password/change")
    public String getPasswordChange(Model model, @ModelAttribute PasswordForm form) {
        return "z/password_change";
    }

    /**
     * パスワード変更.
     * 
     * @param model         model
     * @param form          form
     * @param bindingResult bindingResult
     * @param user          user
     * @return redirect:/home
     * @throws ParseException ParseException
     */
    @PostMapping("/password/change")
    public String postPasswordChange(Model model, @ModelAttribute @Validated PasswordForm form,
            BindingResult bindingResult, @AuthenticationPrincipal AppUserDetails user) throws ParseException {

        // 入力チェックに引っかかった場合、パスワード再発行画面に戻る
        if (bindingResult.hasErrors()) {
            return getPasswordChange(model, form);
        }

        // パスワードを暗号化する
        String password = passwordEncoder.encode(form.getNewPassword());

        userSharedService.updatePasswordDate(user.getUser_id(), password);

        return "redirect:/home";
    }
}
