package com.portal.z.password_change.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * パスワード
 */
@Getter
@Setter
public class PasswordForm {
    @NotBlank(message = "{require_check}")
    private String newPassword; /** パスワード */
    
    @NotBlank(message = "{require_check}")
    private String confirmNewPassword;
}
