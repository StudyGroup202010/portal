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
 * パスワード制約バリデータのアノテーション
 */
@Documented
@Constraint(validatedBy = ValidPasswordValidatorImpl.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

    // デフォルトの設定のみなので、Javadocコメントは省略

    @SuppressWarnings("javadoc")
    String message() default "Invalid Password";

    @SuppressWarnings("javadoc")
    Class<?>[] groups() default {};

    @SuppressWarnings("javadoc")
    Class<? extends Payload>[] payload() default {};

}