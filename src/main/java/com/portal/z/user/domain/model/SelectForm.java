package com.portal.z.user.domain.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * ユーザ情報の検索用Form
 */
@Data
public class SelectForm {
    private String user_id; // ユーザーID
    @DateTimeFormat(pattern = "yyyy-MM-dd") // HTMLをDateで指定した場合はこのFormatになります。
    private LocalDate user_due_date_from; // ユーザ有効期限FROM
    @DateTimeFormat(pattern = "yyyy-MM-dd") // HTMLをDateで指定した場合はこのFormatになります。
    private LocalDate user_due_date_to; // ユーザ有効期限TO
}
