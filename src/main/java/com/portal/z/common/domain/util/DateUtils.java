package com.portal.z.common.domain.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Calendar;
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
     * これを使って初期値を設定しても、実際には0001年１月１日にはならず、なぜか0001年1月2日23:41:01になります。
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

    /**
     * 日付比較処理<BR>
     * 
     * date_1とdate_2の新旧を比較します
     * 
     * @param date_1 date_1
     * @param date_2 date_2
     * @return date_1＝date_2の時：0<BR>
     *         date_1＞date_2の時：1<BR>
     *         date_1＜date_2の時：-1
     */
    public int compareDate(Date date_1, Date date_2) {
        return date_1.compareTo(date_2);
    }

    /**
     * 日付演算処理<BR>
     * 
     * 日付に対して加算／減算をします。<BR>
     * 単位を指定すると演算したい日付に対して値が加算されます。<BR>
     * マイナスの値を設定すると、減算されます。
     * 
     * @param date   演算したい日付
     * @param string 単位（YYYY,MM,DD,HH,MI,SS）
     * @param value  演算したい値
     * @return Date<BR>
     *         誤った単位を入力したときはnull
     */
    public Date calcDate(Date date, String string, int value) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (string.equals("YYYY")) {
            calendar.add(Calendar.YEAR, value); // 年
        } else if (string.equals("MM")) {
            calendar.add(Calendar.MONTH, value); // 月
        } else if (string.equals("DD")) {
            calendar.add(Calendar.DAY_OF_MONTH, value); // 日
        } else if (string.equals("HH")) {
            calendar.add(Calendar.HOUR_OF_DAY, value); // 時
        } else if (string.equals("MI")) {
            calendar.add(Calendar.MINUTE, value); // 分
        } else if (string.equals("SS")) {
            calendar.add(Calendar.SECOND, value); // 秒
        } else {
            return null;
        }
        return calendar.getTime();
    }
}
