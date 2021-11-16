package com.portal.z.common.domain.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

/**
 * ユーザマスタ
 */
@Data
public class User {
    private String user_id; // ユーザーID
    private Date user_due_date; // ユーザ有効期限
    private String password; // パスワード
    private Date pass_update; // パスワード有効期限
    private int login_miss_times; // ログイン失敗回数
    private boolean lock_flg; // ロック状態
    private String employee_id; // 社員ID
    private String employee_cd; // 社員CD
    private String employee_name1_last; // 社員名漢字（姓）
    private boolean enabled_flg; // 有効フラグ
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}
