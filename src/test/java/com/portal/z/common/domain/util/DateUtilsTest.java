package com.portal.z.common.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * com.portal.z.common.domain.util.DateUtils のテストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:postgresql://localhost:5432/SAMPLE",
        "DATASOURCE_PASSWORD=admin", "DATASOURCE_USERNAME=postgres" })
class DateUtilsTest {

    /*
     * 注意：
     * 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。
     *   【標準の設定】
     *   DATASOURCE_URL jdbc:postgresql://localhost:5432/SAMPLE
     *   DATASOURCE_USERNAME postgres
     *   DATASOURCE_PASSWORD admin
     */

    @Autowired
    private DateUtils dateUtils;

    //
    // getStringFromDate
    //
    @Test
    final void dateUtils_getStringFromDate_null入力チェック() {
        assertThat(dateUtils.getStringFromDate(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getStringFromDate_通常日付入力チェック() {
        assertThat(dateUtils.getStringFromDate(new Date(0))).isEqualTo("19700101");
    }

    //
    // getDateFromString
    //
    @Test
    final void dateUtils_getDateFromString_null入力チェック() {
        assertThat(dateUtils.getDateFromString(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getDateFromString_通常日付入力チェック() throws ParseException {
        assertThat(dateUtils.getDateFromString("19700101")).isEqualTo("1970-01-01T00:00:00.000");
    }

    //
    // setStartDate
    //
    @Test
    final void dateUtils_setStartDate_通常日付入力チェック() {
        assertThat(dateUtils.setStartDate(new Date(0))).isEqualTo("1970-01-01T09:00:00.000");
    }

    @Test
    final void dateUtils_setStartDate_null入力チェック() {
        // 結果が１年ずれてしまう・・・
        assertThat(dateUtils.setStartDate(null)).isEqualTo("0001-01-02T23:41:01.000");
    }

    //
    // setEndDate
    //
    @Test
    final void dateUtils_setEndDate_通常日付入力チェック() {
        assertThat(dateUtils.setEndDate(new Date(0))).isEqualTo("1970-01-01T09:00:00.000");
    }

    @Test
    final void dateUtils_setEndDate_null入力チェック() {
        assertThat(dateUtils.setEndDate(null)).isEqualTo("9999-12-31");
    }
}
