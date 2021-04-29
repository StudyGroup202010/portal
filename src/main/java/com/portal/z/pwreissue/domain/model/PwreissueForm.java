package com.portal.z.pwreissue.domain.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * パスワード再設定画面の入力用Form
 *
 */
@Data
public class PwreissueForm {
    @NotBlank(message = "{require_check}")
    private String user_id; // ユーザID
}
