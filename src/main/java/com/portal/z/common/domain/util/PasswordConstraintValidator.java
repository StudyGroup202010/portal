package com.portal.z.common.domain.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.collections4.CollectionUtils;
import org.passay.CharacterRule;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import lombok.extern.slf4j.Slf4j;

/**
 * パスワード制約バリデータ
 * 
 * パスワード項目の独自チェックを実行する。<br>
 * 資料：http://www.passay.org/reference/
 */
@Slf4j
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        // メッセージの日本語化
        // passayのデフォルトのルールを使う場合、メッセージ定数はpassayの定数に合わせてください。
        // →http://www.passay.org/reference/
        Properties props = new Properties();
        try {
            // プロパティファイルからメッセージを読み込む。
            props.load(new InputStreamReader(new FileInputStream("src/main/resources/messages.properties"), "UTF-8"));
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException@PasswordConstraintValidator:");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("IOException@PasswordConstraintValidator:");
            e.printStackTrace();
        }
        MessageResolver resolver = new PropertiesMessageResolver(props);
        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                // 文字の桁数 長さ4から100文字まで
                new LengthRule(4, 100),

                // 記号(-_!@)を1文字以上含む
                new CharacterRule(SpecialCharacterData.Special, 1),
                
                // 空白を認めない ”P ASS”や”PASS ”はエラーにする。
                new WhitespaceRule()

        ));

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = "";
        // 複数のエラーがあった場合、最初の1件を表示する。
        // TODO N件のエラーがあった場合、すべて出力するか？
        if (!CollectionUtils.isEmpty(messages)) {
            messageTemplate = messages.get(0);
        }
        context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
