package com.portal.z.resetpassword.controller;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.service.UserSharedService;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.resetpassword.domain.model.ResetpasswordForm;
import com.portal.z.resetpassword.domain.service.ResetpasswordService;

import lombok.extern.slf4j.Slf4j;

/**
 * パスワード再発行画面用Controller
 *
 */
@Controller
@Slf4j
public class ResetpasswordController {

    @Autowired
    ResetpasswordService resetpasswordService;

    @Autowired
    private UserSharedService userSharedService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 画面表示.
     * 
     * @param form  form
     * @param model model
     * @return z/createReissueInfoForm
     */
    @GetMapping("/resetpassword")
    public String getResetpassword(@ModelAttribute ResetpasswordForm form, Model model) {

        // パスワード再発行情報を検索する。
        Pwreissueinfo pwreissueinfo = resetpasswordService.selectOne(form.getToken());

        if (pwreissueinfo == null) {
            // 認証情報が無ければログイン画面に戻る。
            model.addAttribute("result", massageUtils.getMsg("e.co.fw.2.006", null));
            return "z/login";
        }

        if (DateUtils.compareDateTime(LocalDateTime.now(), pwreissueinfo.getExpirydate().toLocalDateTime()) != -1) {
            // 認証情報有効期限外
            model.addAttribute("result", massageUtils.getMsg("w.co.fw.2.001", null));
            return "z/login";
        }

        // ユーザーIDに初期値をセットする。
        form.setUser_id(pwreissueinfo.getUser_id());
        model.addAttribute("ResetpasswordForm", form);
        return "z/resetpassword";
    }

    /**
     * パスワード再発行情報登録
     * 
     * @param model         model
     * @param form          form
     * @param bindingResult bindingResult
     * @return パスワード再発行画面
     * @throws ParseException ParseException
     */
    @PostMapping("/resetpassword")
    public String postResetpassword(Model model, @ModelAttribute @Validated ResetpasswordForm form,
            BindingResult bindingResult) throws ParseException {

        // 入力チェックに引っかかった場合、パスワード再発行画面に戻る
        if (bindingResult.hasErrors()) {
            return getResetpassword(form, model);
        }

        // 入力した仮パスワードと秘密情報があっていなければエラー
        boolean result1 = resetpasswordService.checksecret(form.getToken(), form.getSecret());

        if (result1 == false) {
            model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.007", null));
            return getResetpassword(form, model);
        }

        // パスワードを暗号化する
        String password = passwordEncoder.encode(form.getNewPassword());

        // Userマスタのパスワードを、入力したパスワードで更新する。
        boolean result2 = userSharedService.updatePasswordDate(form.getUser_id(), password);

        if (result2 == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // パスワード再発行情報を削除する（パスワードの更新が失敗していても削除してしまう）
        resetpasswordService.deletePwreissueinfoByUserid(form.getUser_id());

        return "z/login";
    }
}
