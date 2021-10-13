package com.portal.z.common.domain.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * com.portal.z.common.domain.util.DateUtils のテストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:postgresql://localhost:5432/portal",
        "DATASOURCE_PASSWORD=admin", "DATASOURCE_USERNAME=postgres" })
class DateUtilsTest {

    /*
     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
     * DATASOURCE_PASSWORD admin
     */
    //
    // getStringFromDate
    //
    @Test
    final void dateUtils_getStringFromDate_null入力チェック() {
        assertThat(DateUtils.getStringFromDate(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getStringFromDate_通常日付入力チェック() {
        assertThat(DateUtils.getStringFromDate(LocalDate.of(1970, 01, 01))).isEqualTo("19700101");
    }

    //
    // getStringFromDateFormat
    //
    @Test
    final void dateUtils_getStringFromDateFormat_null入力チェック() {
        assertThat(DateUtils.getStringFromDateFormat(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getStringFromDateFormat_通常日付入力チェック() {
        assertThat(DateUtils.getStringFromDateFormat(LocalDate.of(1970, 01, 01))).isEqualTo("1970/01/01");
    }

    //
    // getStringFromDateTime
    //
    @Test
    final void dateUtils_getStringFromDateTime_null入力チェック() {
        assertThat(DateUtils.getStringFromDateTime(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getStringFromDateTime_通常日付入力チェック() {
        assertThat(DateUtils.getStringFromDateTime(LocalDateTime.of(1970, 01, 01, 12, 31, 51)))
                .isEqualTo("19700101 123151");
    }

    //
    // getStringFromDateTimeFormat
    //
    @Test
    final void dateUtils_getStringFromDateTimeFormat_null入力チェック() {
        assertThat(DateUtils.getStringFromDateTimeFormat(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getStringFromDateTimeFormat_通常日付入力チェック() {
        assertThat(DateUtils.getStringFromDateTimeFormat(LocalDateTime.of(1970, 01, 01, 12, 31, 51)))
                .isEqualTo("1970/01/01 12:31:51");
    }

    //
    // getDateFromString
    //
    @Test
    final void dateUtils_getDateFromString_null入力チェック() {
        assertThat(DateUtils.getDateFromString(null)).isNull();
    }

    @Test
    final void dateUtils_getDateFromString_通常日付入力チェック() {
        assertThat(DateUtils.getDateFromString("19700101")).isEqualTo("1970-01-01");
    }

    @Test
    final void dateUtils_getDateFromString_異常日付入力チェック1() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("700101");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '700101' could not be parsed at index 6");
        }
    }
    
    @Test
    final void dateUtils_getDateFromString_異常日付入力チェック2() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("20211240");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '20211240' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 40");
        }
    }
    
    @Test
    final void dateUtils_getDateFromString_異常日付入力チェック3() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("202112011");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '202112011' could not be parsed at index 0");
        }
    }
    
    //
    // getDateFromStringmonth
    //
    @Test
    final void dateUtils_getDateFromStringmonth_null入力チェック() {
        assertThat(DateUtils.getDateFromString(null)).isNull();
    }

    @Test
    final void dateUtils_getDateFromStringmonth_通常日付入力チェック() {
        assertThat(DateUtils.getDateFromStringmonth("197001")).isEqualTo("1970-01-01");
    }

    @Test
    final void dateUtils_getDateFromStringmonth_異常日付入力チェック1() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("20211");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '20211' could not be parsed at index 4");
        }
    }
    
    @Test
    final void dateUtils_getDateFromStringmonth_異常日付入力チェック2() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("20213");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '20213' could not be parsed at index 4");
        }
    }
    
    @Test
    final void dateUtils_getDateFromStringmonth_異常日付入力チェック3() {
        // 変換できない文字が入力されたらDateTimeParseExceptionを投げる。
        try {
            DateUtils.getDateFromString("202121");
            fail();
        } catch (final Exception e) {
            assertThat(e.toString())
                    .isEqualTo("java.time.format.DateTimeParseException: Text '202121' could not be parsed at index 6");
        }
    }

    //
    // setStartDate
    //
    @Test
    final void dateUtils_setStartDate_通常日付入力チェック() {
        assertThat(DateUtils.setStartDate(LocalDate.of(1970, 01, 01))).isEqualTo("1970-01-01");
    }

    @Test
    final void dateUtils_setStartDate_null入力チェック() {
        assertThat(DateUtils.setStartDate(null)).isEqualTo("0000-01-01");
    }

    //
    // setEndDate
    //
    @Test
    final void dateUtils_setEndDate_通常日付入力チェック() {
        assertThat(DateUtils.setEndDate(LocalDate.of(1970, 01, 01))).isEqualTo("1970-01-01");
    }

    @Test
    final void dateUtils_setEndDate_null入力チェック() {
        assertThat(DateUtils.setEndDate(null)).isEqualTo("9999-12-31");
    }

    //
    // compareDate
    //
    @Test
    final void dateUtils_compareDate_nullチェック1() {
        LocalDateTime date_2 = LocalDateTime.now();
        assertThat(DateUtils.compareDateTime(null, date_2)).isEqualTo(-1);
    }

    @Test
    final void dateUtils_compareDate_nullチェック2() {
        LocalDateTime date_1 = LocalDateTime.now();
        assertThat(DateUtils.compareDateTime(date_1, null)).isEqualTo(1);
    }
    
    @Test
    final void dateUtils_compareDate_nullチェック3() {
        assertThat(DateUtils.compareDateTime(null, null)).isEqualTo(0);
    }
    
    @Test
    final void dateUtils_compareDate_等しいチェック() {
        LocalDateTime date_1 = LocalDateTime.now();
        LocalDateTime date_2 = date_1;
        assertThat(DateUtils.compareDateTime(date_1, date_2)).isEqualTo(0);
    }

    @Test
    final void dateUtils_compareDate_date_1が新しいチェック() {
        LocalDateTime date_1 = LocalDateTime.now();
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime date_2 = LocalDateTime.of(date, time);
        assertThat(DateUtils.compareDateTime(date_1, date_2)).isEqualTo(1);
    }

    @Test
    final void dateUtils_compareDate_date_2が新しいチェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime date_1 = LocalDateTime.of(date, time);
        LocalDateTime date_2 = LocalDateTime.now();
        assertThat(DateUtils.compareDateTime(date_1, date_2)).isEqualTo(-1);
    }

    //
    // calcDate
    //
    @Test
    final void dateUtils_calcDate_引数チェック1() {
        assertThat(DateUtils.calcDate(null, "YYYY", 1)).isNull();
    }
    
    @Test
    final void dateUtils_calcDate_引数エラーチェック_様式() {
        LocalDateTime date = LocalDateTime.now();
        assertThat(DateUtils.calcDate(date, "XX", 1)).isNull();
    }

    @Test
    final void dateUtils_calcDate_年加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "YYYY", 1)).isEqualTo("1971-01-01T09:00:00.000");
    }

    @Test
    final void dateUtils_calcDate_月加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "MM", 1)).isEqualTo("1970-02-01T09:00:00.000");
    }

    @Test
    final void dateUtils_calcDate_日加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "DD", 1)).isEqualTo("1970-01-02T09:00:00.000");
    }

    @Test
    final void dateUtils_calcDate_時加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "HH", 1)).isEqualTo("1970-01-01T10:00:00.000");
    }

    @Test
    final void dateUtils_calcDate_分加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "MI", 1)).isEqualTo("1970-01-01T09:01:00.000");
    }

    @Test
    final void dateUtils_calcDate_秒加算チェック() {
        LocalDate date = LocalDate.of(1970, 01, 01);
        LocalTime time = LocalTime.of(9, 00, 00);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        assertThat(DateUtils.calcDate(datetime, "SS", 1)).isEqualTo("1970-01-01T09:00:01.000");
    }
    
    //
    // chkYearMonthFromString
    //
    @Test
    final void dateUtils_chkYearMonthFromString_null入力チェック() {
        assertThat(DateUtils.chkYearMonthFromString(null)).isFalse();
    }

    @Test
    final void dateUtils_chkYearMonthFromString_異常日付入力チェック１() {
        // フォーマットが異常なデータ
        assertThat(DateUtils.chkYearMonthFromString("19701")).isFalse();
    }
    
    @Test
    final void dateUtils_chkYearMonthFromString_異常日付入力チェック２() {
        // フォーマットが異常なデータ
        assertThat(DateUtils.chkYearMonthFromString("1970121")).isFalse();
    }
    
    @Test
    final void dateUtils_chkYearMonthFromString_異常日付入力チェック３() {
        // フォーマットが異常なデータ
        assertThat(DateUtils.chkYearMonthFromString("197013")).isFalse();
    }
    
    @Test
    final void dateUtils_chkYearMonthFromString_異常日付入力チェック４() {
        // フォーマットが異常なデータ
        assertThat(DateUtils.chkYearMonthFromString("197000")).isFalse();
    }
    
    @Test
    final void dateUtils_chkYearMonthFromString_正常日付入力チェック() {
        assertThat(DateUtils.chkYearMonthFromString("197011")).isTrue();
    }  
}
