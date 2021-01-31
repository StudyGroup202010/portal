package com.portal.z.user.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SelectForm {
    private String user_id; // ユーザーID
    @DateTimeFormat(pattern = "yyyy-MM-dd") // HTMLをDateで指定した場合はこのFormatになります。
    private Date user_due_date_from; // ユーザ有効期限FROM
    @DateTimeFormat(pattern = "yyyy-MM-dd") // HTMLをDateで指定した場合はこのFormatになります。
    private Date user_due_date_to; // ユーザ有効期限TO
}
