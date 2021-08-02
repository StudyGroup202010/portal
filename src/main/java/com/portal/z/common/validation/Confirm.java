package com.portal.z.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * パスワード比較バリデータをアノテーションで使用するためのインターフェース<BR>
 * 
 * 使い方： @Confirm(field = "newPassword") ・・・　「newPassword」と「confirm newPassword」の値を比較する。
 *
 */
@Documented
@Constraint(validatedBy = { ConfirmValidatorImpl.class }) // 実装クラスを指定。
@Target({ TYPE, ANNOTATION_TYPE }) // このアノテーションが付加できる対象を絞る。
@Retention(RUNTIME) // アノテーションが影響する範囲。
public @interface Confirm {
    /**
     * @return メッセージ
     */
    String message() default "";

    /**
     * @return 空のgroups()
     */
    Class<?>[] groups() default {};

    /**
     * @return 空のpayload()
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * @return アノテーションに渡す項目名
     */
    String field(); // アノテーションに渡す項目
}