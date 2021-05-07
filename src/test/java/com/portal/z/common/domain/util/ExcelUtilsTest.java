package com.portal.z.common.domain.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * com.portal.z.common.domain.util.ExcelUtils のテストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:postgresql://localhost:5432/portal",
        "DATASOURCE_PASSWORD=admin", "DATASOURCE_USERNAME=postgres" })
class ExcelUtilsTest {

    @Autowired
    private ExcelUtils excelUtils;

    private String path = "src\\main\\resources\\templates\\excel\\Excel_File_For_Testing.xlsx";

    /*
     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
     * DATASOURCE_PASSWORD admin
     */

    //
    // getColumnString
    //

    @Test
    final void dateUtils_getColumnString_NULLチェック() {
        assertThat(excelUtils.getColumnString(null, 1, 4)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnString_0チェック1() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnString(row, 0, 1)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnString_0チェック2() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnString(row, 1, 0)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnString_文字値の取得チェック1() throws IOException {
        // 取得したいセルに値がセットされていないとき。（何もないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(2);

        assertThat(excelUtils.getColumnString(row, 1, 4)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnString_文字値の取得チェック2() throws IOException {
        // 取得したいセルに値がセットされていないとき。（値がセットされていないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnString(row, 2, 4)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnString_文字値の取得チェック3() throws IOException {
        // 取得したいセルに文字以外がセットされていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnString(row, 2, 4);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.015]：エクセルファイルのNUMERICシートの6行2列に登録された値が文字ではありません。");
        }
    }

    @Test
    final void dateUtils_getColumnString_文字列の取得チェック4() throws IOException {
        // 取得したいセルの文字が指定桁数より大きいとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnString(row, 1, 4);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.016]：エクセルファイルのSTRINGシートの6行1列に登録された値の桁数は4桁以内にしてください。");
        }
    }

    @Test
    final void dateUtils_getColumnString_文字列の取得チェック5() throws IOException {
        // セルの文字を取得する。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnString(row, 1, 10)).isEqualTo("Stringtest");
    }

    //
    // getColumnSmallint
    //

    @Test
    final void dateUtils_getColumnSmallint_NULLチェック() {
        assertThat(excelUtils.getColumnSmallint(null, 1)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnSmallint_0チェック() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnSmallint(row, 0)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック1() throws IOException {
        // 取得したいセルに値がセットされていないとき。（何もないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(2);

        assertThat(excelUtils.getColumnSmallint(row, 1)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック2() throws IOException {
        // 取得したいセルに値がセットされていないとき。（値がセットされていないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnSmallint(row, 1)).isEqualTo(null);

    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック3() throws IOException {
        // 取得したいセルに数値以外がセットされていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnSmallint(row, 1);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.017]：エクセルファイルのSTRINGシートの6行1列に登録された値が数値ではありません。");
        }
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック4() throws IOException {
        // 取得したいセルの数値が指定桁数より小さいとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(7);

        try {
            excelUtils.getColumnSmallint(row, 2);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.019]：エクセルファイルのNUMERICシートの8行2列に登録された値が整数の範囲を超えています。");
        }
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック5() throws IOException {
        // 取得したいセルの数値が指定桁数より大きいとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(6);

        try {
            excelUtils.getColumnSmallint(row, 2);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.019]：エクセルファイルのNUMERICシートの7行2列に登録された値が整数の範囲を超えています。");
        }
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック6() throws IOException {
        // 取得したいセルの数値に小数点が登録されていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnSmallint(row, 3);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.020]：エクセルファイルのNUMERICシートの6行3列に登録された値に小数点が登録されています。");
        }
    }

    @Test
    final void dateUtils_getColumnSmallint_数値の取得チェック7() throws IOException {
        // セルの数値を取得する。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnSmallint(row, 2)).isEqualTo("12345");
    }

    //
    // getColumnDate
    //

    @Test
    final void dateUtils_getColumnDate_NULLチェック() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("DATE");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnDate(row, 0)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック1() throws IOException {
        // 取得したいセルに値がセットされていないとき。（何もないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("DATE");
        Row row = sheet.getRow(2);

        assertThat(excelUtils.getColumnDate(row, 1)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック2() throws IOException {
        // 取得したいセルに値がセットされていないとき。（値がセットされていないセル）
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("DATE");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnDate(row, 1)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック3() throws IOException {
        // 取得したいセルに日付以外の値（文字列）が登録されていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("STRING");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnDate(row, 1);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.018]：エクセルファイルのSTRINGシートの6行1列に登録された値に日付以外の値が登録されています。");
        }
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック4() throws IOException {
        // 取得したいセルに日付以外の値（数値）が登録されていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("NUMERIC");
        Row row = sheet.getRow(5);

        try {
            excelUtils.getColumnDate(row, 4);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.018]：エクセルファイルのNUMERICシートの6行4列に登録された値に日付以外の値が登録されています。");
        }
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック5() throws IOException {
        // 取得したいセルに日付に変換できない日付が登録されていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("DATE");
        Row row = sheet.getRow(6);

        try {
            excelUtils.getColumnDate(row, 2);
            fail();
        } catch (final Exception e) {
            assertThat(e.getMessage()).isEqualTo("[e.co.fw.1.018]：エクセルファイルのDATEシートの7行2列に登録された値に日付以外の値が登録されています。");
        }
    }

    @Test
    final void dateUtils_getColumnDate_日付の取得チェック7() throws IOException {
        // 取得したいセルに日付が登録されていたとき。
        InputStream is = Files.newInputStream(Paths.get(path));
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheet("DATE");
        Row row = sheet.getRow(5);

        assertThat(excelUtils.getColumnDate(row, 2)).isEqualTo("2021-12-31T00:00:00.000");
        assertThat(excelUtils.getColumnDate(row, 3)).isEqualTo("1899-12-31T13:56:15.000");
        assertThat(excelUtils.getColumnDate(row, 4)).isEqualTo("2021-12-31T13:15:52.000");
    }
}
