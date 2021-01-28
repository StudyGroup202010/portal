package com.portal.z.user.domain.model;

import lombok.Data;

@Data
public class SelectForm {
    private String user_id; // ユーザーID
    private String user_due_date_from; // ユーザ有効期限FROM
    private String user_due_date_to; // ユーザ有効期限TO
}
