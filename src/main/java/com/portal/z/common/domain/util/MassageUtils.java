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
     * 例） getMsg("message_id01", new String[] { "arg01", "arg02" });
     * メッセージIDがinfoではないときは、メッセージの先頭にメッセージIDが付与されます。
     * 
     * @param message_id メッセージID
     * @param args       パラメータ
     * @return メッセージ
     */
    public String getMsg(String message_id, Object[] args) {

        String message = messageSource.getMessage(message_id, args, Constants.NOT_FOUND_MESSAGE, Locale.getDefault());

        if ("i".compareTo(message_id.substring(0, 1)) != 0) {
            message = "[" + message_id + "]：" + message;
        }
        return message;
    }
}
