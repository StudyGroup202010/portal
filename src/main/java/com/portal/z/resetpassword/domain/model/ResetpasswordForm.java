package com.portal.z.resetpassword.domain.model;

import javax.validation.constraints.NotBlank;

import com.portal.z.common.validation.Confirm;
import com.portal.z.common.validation.ValidPassword;

import lombok.Data;

/**
 * パスワード再発行画面の入力用Form
 *
 */
@Data
@Confirm(field = "newPassword")
public class ResetpasswordForm {
    @NotBlank(message = "{require_check}")
    private String user_id; // ユーザID

    @NotBlank(message = "{require_check}")
    private String token;

    @NotBlank(message = "{require_check}")
    private String secret;

    @NotBlank(message = "{require_check}")
    @ValidPassword
    private String newPassword;

    @NotBlank(message = "{require_check}")
    @ValidPassword
    private String confirmNewPassword;
}
