package com.portal.z.common.domain.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component("DateUtils")
public class DateUtils {
	
    /*
     * 日付の初期値
     * 実際には0001年１月１日にはならず、0001年1月2日23:41:01になります。
     */
    private static final String DEFAULT_START_DATE = "00010101";

    /*
     * 日付の永遠値
     */
    private static final String DEFAULT_END_DATE = "99991231";

    /*
     * 日付文字列変換処理
     * 
     * dateに該当する文字列を取得します。（様式はYYYYMMDD） 
     * dateがブランクの場合、nullを返します。
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。
     * 
     * @param date 変換元の日付
     * @return 文字型に変換したdate
     * 
     */
    public String getStringFromDate(Date date) {
        if (date == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        // Date型をLocalDate型に変換して使います。
        return formatter.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
	
    /*
     * 文字列日付変換処理
     * 
     * dateに該当する日付取得します。 
     * dateがブランクの場合、nullを返します。
     * 日付変換にはjava.time.fomat.DateTimeFormatterを使っています。
     * 
     * @param date 変換元の文字列（様式はYYYYMMDD）
     * @return Date型に変換したdate
     * 
     */
    public Date getDateFromString(String date) {
        if (date == null) {
            return null;
        }
        // 変換元の文字列のフォーマットを指定します。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        // 日付変換したLocalDate型の値をDate型に変換して返します。
        return Date.from(LocalDate.parse(date, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /*
     * 日付初期値設定処理
     * 
     * dateがnullの場合に日付の初期値を設定します。 dateがnullではない場合、そのまま返します。
     * 
     * @param date 変換元の日付
     * 
     * @return 初期値に変換したdate
     * 
     */
    public Date setStartDate(Date date) {
        if (date != null) {
            return date;
        }
        return getDateFromString(DEFAULT_START_DATE);
    }

    /*
     * 日付永遠設定処理
     * 
     * dateがnullの場合に日付の永遠値を設定します。 dateがnullではない場合、そのまま返します。
     * 
     * @param date 変換元の日付
     * 
     * @return 永遠値に変換したdate
     * 
     */
    public Date setEndDate(Date date) {
        if (date != null) {
            return date;
        }
        return getDateFromString(DEFAULT_END_DATE);
    }
}
