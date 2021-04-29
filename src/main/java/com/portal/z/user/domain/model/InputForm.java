package com.portal.z.user.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    // (修正前)必須入力、長さ4から100桁まで、半角英数字のみ
    // 必須入力
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    // TODO パスワード制約チェックと内容が重複するが、メッセージが変わってしまうため、残しています
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\_!@]+$", groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{pattern_check}")
    // (修正後)パスワード制約チェック ※特殊文字を含むこと、文字数の長さをチェックしています
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
