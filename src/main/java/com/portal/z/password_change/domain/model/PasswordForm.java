package com.portal.z.password_change.domain.model;

import javax.validation.constraints.NotBlank;

import com.portal.z.common.validation.Confirm;

import lombok.Getter;
import lombok.Setter;

/**
 * パスワード
 */
@Getter
@Setter
@Confirm(field = "newPassword")
public class PasswordForm {
    @NotBlank(message = "{require_check}")
    private String newPassword;

    @NotBlank(message = "{require_check}")
    private String confirmNewPassword;
}
