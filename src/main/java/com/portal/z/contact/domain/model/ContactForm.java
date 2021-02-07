package com.portal.z.contact.domain.model;

import lombok.Data;

/**
 * 問い合わせ画面の入力用Form
 */
@Data
public class ContactForm {
    private String name;
    private String email;
    private String message;
}