package com.portal.z.common.validation;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * パスワード制約バリデータをアノテーションで使用するためのインターフェース
 */
@Documented
@Constraint(validatedBy = ValidPasswordValidatorImpl.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

    /**
     * メッセージ(チェックのメッセージ)
     * 
     * @return メッセージ(デフォルト： "Invalid Password")
     */
    String message() default "Invalid Password";

    /**
     * グループ指定(エラーのチェック順)
     * 
     * @return グループ情報
     */
    Class<?>[] groups() default {};

    /**
     * ペイロード(バリデーション付加情報)
     * 
     * @return ペイロード情報
     */
    Class<? extends Payload>[] payload() default {};

}