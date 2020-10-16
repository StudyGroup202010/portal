package com.portal.z.password_change.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
}
