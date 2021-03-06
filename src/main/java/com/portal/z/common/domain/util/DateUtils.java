package com.portal.z.common.domain.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Component;

/**
 * Date関係のユーティリティ
 *
 */
@Component("DateUtils")
public class DateUtils {

    /**
     * 日付の初期値（"00010101"）。<BR>
     * これを使って初期値を設定しても、実際には0001年１月１日にはならず、0001年1月2日23:41:01になります。
     */
    public final String DEFAULT_START_DATE = "00010101";

    /**
     * 日付の永遠値（"99991231"）
     */
    public final String DEFAULT_END_DATE = "99991231";

    /**
     * 日付⇒文字列変換処理<BR>
     * 
     * 入力したdateに該当する文字列を取得します。（様式はYYYYMMDD） <BR>
     * dateがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の日付
     * @return String型に変換したdate
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

    /**
     * 文字列⇒日付変換処理<BR>
     * 
     * dateに該当する日付を取得します。 dateがブランクの場合、nullを返します。<BR>
     * 日付変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の文字列（様式はYYYYMMDD）
     * @return Date型に変換したdate
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

    /**
     * 初期日付値設定処理<BR>
     * 
     * dateがnullの場合に日付の初期値を設定します。 dateがnullではない場合、そのまま返します。
     * 
     * @param date 変換元の日付
     * @return 初期値に変換したdate
     */
    public Date setStartDate(Date date) {
        if (date != null) {
            return date;
        }
        return getDateFromString(DEFAULT_START_DATE);
    }

    /**
     * 永遠日付設定処理<BR>
     * 
     * dateがnullの場合に日付の永遠値を設定します。 dateがnullではない場合、そのまま返します。
     * 
     * @param date 変換元の日付
     * @return 永遠値に変換したdate
     */
    public Date setEndDate(Date date) {
        if (date != null) {
            return date;
        }
        return getDateFromString(DEFAULT_END_DATE);
    }
}
