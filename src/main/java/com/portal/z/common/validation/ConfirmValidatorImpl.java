package com.portal.z.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.portal.z.common.domain.util.MassageUtils;

/**
 * パスワード比較バリデータ
 * 
 * パスワードとパスワード（確認）の値を比較する。<br>
 *
 */
public class ConfirmValidatorImpl implements ConstraintValidator<Confirm, Object> {

    @Autowired
    private MassageUtils massageUtils;

    private String message;
    private String field;
    private String confirmField;

    /**
     * 初期化
     * 
     * メッセージキー：e.co.fw.1.008
     * 
     */
    public void initialize(Confirm constraintAnnotation) {
        message = massageUtils.getMsg("e.co.fw.1.008", null);
        field = constraintAnnotation.field();
        confirmField = "confirm" + StringUtils.capitalize(field);
    }

    /**
     * 項目の値を比較する。<BR>
     * true ：同じ値がセットされていたとき。<BR>
     * false：異なる値がセットされていたとき
     */
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field); // フォームオブジェクトからfield値を取得
        Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField); // フォームオブジェクトからconfirmField値を取得
        
        boolean matched = ObjectUtils.nullSafeEquals(fieldValue, confirmFieldValue); // 値を比較
        if (matched) {
            return true; // 値がマッチしたとき
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation(); // エラーメッセージを出力したいフィールド名とメッセージ
            return false; // 値がマッチしない
        }
    }
}