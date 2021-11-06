package com.portal.z.common.domain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
 * Date関係のユーティリティ
 *
 */
public final class DateUtils {

    // 他からインスタンス起動できないよう、コンストラクタをprivateで定義する。
    private DateUtils() {

    }

    /**
     * 日付の初期値（"00000101"）<BR>
     * （注）日付に変換可能な文字列を登録すること
     */
    public static String DEFAULT_START_DATE = "00000101";

    /**
     * 日付の永遠値（"99991231"）<BR>
     * （注）日付に変換可能な文字列を登録すること
     */
    public static String DEFAULT_END_DATE = "99991231";

    /**
     * 日付（年月日）⇒文字列(YYYYMMDD)変換処理<BR>
     * 
     * 入力したdateに該当する文字列を取得します。（様式はYYYYMMDD） <BR>
     * dateがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の日付
     * @return String型に変換したdate
     */
    public static String getStringFromDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(date);
    }

    /**
     * 日付（年月日）⇒文字列(YYYY/MM/DD)変換処理<BR>
     * 
     * 入力したdateに該当する文字列を取得します。（様式はYYYY/MM/DD） <BR>
     * dateがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の日付
     * @return String型に変換したdate（YYYY/MM/DD）
     */
    public static String getStringFromDateFormat(LocalDate date) {
        if (date == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(date);
    }

    /**
     * 日付（年月日）⇒文字列(YYYY年MM月DD日)変換処理<BR>
     * 
     * 入力したdateに該当する文字列を取得します。（様式はYYYY年MM月DD日） <BR>
     * dateがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の日付
     * @return String型に変換したdate（YYYY年MM月DD日）
     */
    public static String getStringFromDateFormat1(LocalDate date) {
        if (date == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu年MM月dd日").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(date);
    }

    /**
     * 日付（年月日）⇒文字列(YYYY年MM月DD日（E）)変換処理<BR>
     * 
     * 入力したdateに該当する文字列を取得します。（様式はYYYY年MM月DD日（E）） <BR>
     * dateがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の日付
     * @return String型に変換したdate（YYYY年MM月DD日（E））
     */
    public static String getStringFromDateFormat2(LocalDate date) {
        if (date == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu年MM月dd日（E）").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(date);
    }

    /**
     * 日付（年月日時分秒）⇒文字列(YYYYMMDD HHmmss)変換処理<BR>
     * 
     * 入力したdatetimeに該当する文字列を取得します。（様式はYYYYMMDD HHmmss） <BR>
     * datetimeがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param datetime 変換元の日時
     * @return String型に変換したdatetime
     */
    public static String getStringFromDateTime(LocalDateTime datetime) {
        if (datetime == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd HHmmss").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(datetime);
    }

    /**
     * 日付（年月日時分秒）⇒文字列(YYYY/MM/DD HH:mm:ss)変換処理<BR>
     * 
     * 入力したdatetimeに該当する文字列を取得します。（様式はYYYY/MM/DD HH:mm:ss） <BR>
     * datetimeがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param datetime 変換元の日時
     * @return String型に変換したdatetime
     */
    public static String getStringFromDateTimeFormat1(LocalDateTime datetime) {
        if (datetime == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(datetime);
    }

    /**
     * 日付（年月日時分秒）⇒文字列(YYYYMMDDHHmmss)変換処理<BR>
     * 
     * 入力したdatetimeに該当する文字列を取得します。（様式はYYYYMMDDHHmmss） <BR>
     * datetimeがブランクの場合、nullを返します。<BR>
     * 文字列変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param datetime 変換元の日時
     * @return String型に変換したdatetime
     */
    public static String getStringFromDateTimeFormat2(LocalDateTime datetime) {
        if (datetime == null) {
            return null;
        }
        // 変換する文字列のフォーマットを決めます。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMddHHmmss").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        return formatter.format(datetime);
    }

    /**
     * 文字列（YYYYMMDD）⇒日付変換処理<BR>
     * 
     * dateに該当する日付を取得します。 dateがブランクの場合、nullを返します。<BR>
     * 日付変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の文字列（様式はYYYYMMDD）
     * @return Date型に変換したdate
     * @throws DateTimeParseException 日付に変換できなかったとき
     */
    public static LocalDate getDateFromString(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        // 変換元の文字列のフォーマットを指定します。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        // 日付変換したLocalDate型の値をDate型に変換して返します。
        return LocalDate.parse(date, formatter);
    }

    /**
     * 文字列（YYYYMM）⇒日付変換処理<BR>
     * 
     * 月初日を追加してdateに該当する日付を取得します。 dateがブランクの場合、nullを返します。<BR>
     * 日付変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param date 変換元の文字列（様式はYYYYMM）
     * @return Date型に変換したdate
     * @throws DateTimeParseException 日付に変換できなかったとき
     */
    public static LocalDate getDateFromStringmonth(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        // 変換元の文字列のフォーマットを指定します。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        // 月初日を追加して日付変換したLocalDate型の値をDate型に変換して返します。
        return LocalDate.parse(date + "01", formatter);
    }

    /**
     * 初期日付値設定処理<BR>
     * 
     * dateがnullの場合に日付の初期値を設定します。 dateがnullではない場合、そのまま返します。
     * 
     * @param date 変換元の日付
     * @return 初期値に変換したdate
     */
    public static LocalDate setStartDate(LocalDate date) {
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
    public static LocalDate setEndDate(LocalDate date) {
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
    public static int compareDateTime(LocalDateTime date_1, LocalDateTime date_2) {

        // 引数チェック
        if (date_1 == null && date_2 == null) {
            return 0;
        }
        if (date_1 == null) {
            return -1;
        }
        if (date_2 == null) {
            return 1;
        }

        if (date_1.isAfter(date_2)) {
            // date_1＞date_2の時
            return 1;
        } else if (date_1.isBefore(date_2)) {
            // date_1＜date_2の時
            return -1;
        }
        // date_1＝date_2の時
        return 0;
    }

    /**
     * 日付加算減算処理<BR>
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
    public static LocalDateTime calcDate(LocalDateTime date, String string, int value) {

        if (date == null) {
            return null;
        }

        if ("YYYY".equals(string)) {
            return date.plusYears(value); // 年
        } else if ("MM".equals(string)) {
            return date.plusMonths(value); // 月
        } else if ("DD".equals(string)) {
            return date.plusDays(value); // 日
        } else if ("HH".equals(string)) {
            return date.plusHours(value); // 時
        } else if ("MI".equals(string)) {
            return date.plusMinutes(value); // 分
        } else if ("SS".equals(string)) {
            return date.plusSeconds(value); // 秒
        }
        return null;
    }

    /**
     * 文字列（YYYYMM）日付チェック処理<BR>
     * 
     * 入力した文字列が日付形式かどうかをチェックします。yearmonthがブランクの場合、nullを返します。<BR>
     * 日付変換にはjava.time.fomat.DateTimeFormatterを使っています。<BR>
     * 
     * @param yearmonth チェック元の文字列（様式はYYYYMM）
     * @return true:入力した文字列がYYYYMMである。<BR>
     *         false：様式が間違えている
     */
    public static boolean chkYearMonthFromString(String yearmonth) {
        if (yearmonth == null || yearmonth.isEmpty()) {
            return false;
        }
        // 変換元の文字列のフォーマットを指定します。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd").withLocale(Locale.JAPANESE)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            // 日付変換したLocalDate型の値をDate型に変換して返します。
            LocalDate.parse(yearmonth + "01", formatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
//        return true;
    }
}
