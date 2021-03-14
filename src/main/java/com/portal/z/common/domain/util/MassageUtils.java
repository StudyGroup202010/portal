package com.portal.z.common.domain.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * メッセージ関係のユーティリティ
 *
 */
@Component("MassageUtils")
public class MassageUtils {

    @Autowired
    private MessageSource messageSource;

    /**
     * メッセージ取得共通処理<br>
     * 
     * messageKeyに該当するメッセージプロパティのメッセージを取得します。<BR>
     * 例） getMsg("messageKey01", new String[] { "arg01", "arg02" });
     * 
     * @param messageKey メッセージプロパティのKEY
     * @param args       パラメータ
     * @return メッセージ(メッセージプロパティのVALUE)
     */
    public String getMsg(String messageKey, Object[] args) {

        return messageSource.getMessage(messageKey, args, Constants.NOT_FOUND_MESSAGE, Locale.getDefault());

    }
}
