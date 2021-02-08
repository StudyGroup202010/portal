package com.portal.z.contact.domain.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 問い合わせ画面の入力用Form
 */
@Data
public class ContactForm {
    @NotBlank(message = "{require_check}")
    private String contact_name; // ユーザID
    @NotBlank(message = "{require_check}")
    private String contact_email; // メールアドレス
    @NotBlank(message = "{require_check}")
    private String contact_message; // お問い合わせ内容
}