package com.portal.z.common.domain.util;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

@Component("Utility")
public class Utility {

    @Autowired
    private MessageSource messageSource;

    /**
     * サーバーに保存されているファイルを取得して、byte配列に変換する.
     */
    public byte[] getFile(String fileName) throws IOException {

        // ファイルシステム（デフォルト）の取得
        FileSystem fs = FileSystems.getDefault();

        // ファイル取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }

    /**
     * メッセージ取得共通処理<br>
     * 
     * messageKeyに該当するメッセージプロパティのメッセージを取得します。
     * messageKeyに該当するメッセージが存在しない場合、MessageKeyNotExistsのメッセージを返します。
     * 
     * @param messageKey メッセージプロパティのKEY
     * @return メッセージ(メッセージプロパティのVALUE)
     */
    public String getMsg(String messageKey) {
        String message = "";

        try {
            message = messageSource.getMessage(messageKey, null, Locale.getDefault());
        } catch (NoSuchMessageException ne) {
            message = messageSource.getMessage("MessageKeyNotExists", null, Locale.getDefault());
        }

        return message;
    }
}
