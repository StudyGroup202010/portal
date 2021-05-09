package com.portal.z.user.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.portal.z.common.validation.ValidPassword;

import lombok.Data;

/**
 * ユーザ情報の入力用Form
 */
@Data
public class InputForm {

    // 必須入力、メールアドレス形式
    @NotBlank(groups = { ValidCreate1.class }, message = "{require_check}")
    @Email(groups = { ValidCreate2.class }, message = "{email_check}")
    private String user_id; // ユーザーID

    // 必須入力
    @NotNull(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate user_due_date; // ユーザ有効期限

    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    // パスワード制約チェック
    @ValidPassword(groups = { ValidCreate2.class, ValidUpdate2.class })
    private String password; // パスワード

    // 必須入力
    @NotNull(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pass_update; // パスワード有効期限

    // 値が0から10までの整数のみ
    // 必須入力チェックを記述しなくても、未入力の時はintのチェックがかかるのでＯＫ
    @Min(value = 0, groups = ValidUpdate1.class, message = "{min_check}")
    @Max(value = 10, groups = ValidUpdate1.class, message = "{max_check}")
    private int login_miss_times; // ログイン失敗回数

    private boolean lock_flg; // ロック状態
    
    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String employee_id; // 社員ID

    private boolean enabled_flg; // 有効フラグ

    // 以下、チェック実装の参考
    // //値が20から100まで
    // @Min(value = 20, groups = ValidGroup2.class, message = "{min_check}")
    // @Max(value = 150, groups = ValidGroup2.class, message = "{max_check}")
    // private int age; // 年齢
    //
    // //falseのみ可能
    // @AssertFalse(groups = ValidGroup2.class, message = "{false_check}")
    // private boolean marriage_flg; // 結婚ステータス
}
