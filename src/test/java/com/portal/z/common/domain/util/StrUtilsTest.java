package com.portal.z.common.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * com.portal.z.common.domain.util.StrUtils のテストクラス
 */
@SpringBootTest
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:mysql://localhost:3306/portal","DATASOURCE_PASSWORD=Portaladmin", "DATASOURCE_USERNAME=root" })
class StrUtilsTest {
    /*
     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
     * DATASOURCE_PASSWORD admin
     */
    //
    // getStrLength
    //
    @Test
    final void dateUtils_getStrLength_null入力チェック() {
        assertThat(StrUtils.getStrLength(null)).isEqualTo(0);
    }

    @Test
    final void dateUtils_getStrLength_文字列チェック1() {
        assertThat(StrUtils.getStrLength("𠮷田太郎")).isEqualTo(4);
    }

    //
    // getSubstring
    //
    @Test
    final void dateUtils_getSubstring_null入力チェック1() {
        assertThat(StrUtils.getSubstring(null, 0, 0)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getSubstring_null入力チェック2() {
        assertThat(StrUtils.getSubstring("", 0, 0)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_null入力チェック3() {
        assertThat(StrUtils.getSubstring("", 0, 2)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_文字列チェック1() {
        assertThat(StrUtils.getSubstring("𠮷田太郎", 1, 0)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_文字列チェック2() {
        assertThat(StrUtils.getSubstring("𠮷田太郎", 1, 5)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_文字列チェック3() {
        assertThat(StrUtils.getSubstring("𠮷田太郎", 6, 7)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_文字列チェック4() {
        assertThat(StrUtils.getSubstring("𠮷田太郎", 4, 4)).isEqualTo("");
    }

    @Test
    final void dateUtils_getSubstring_文字列チェック5() {
        assertThat(StrUtils.getSubstring("𠮷田太郎", 0, 2)).isEqualTo("𠮷田");
    }

    //
    // isFullkana
    //
    @Test
    final void dateUtils_isFullkana_null入力チェック1() {
        assertThat(StrUtils.isFullkana(null)).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック1() {
        assertThat(StrUtils.isFullkana("")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック2() {
        assertThat(StrUtils.isFullkana("ｱｲｳｴｵ")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック3() {
        assertThat(StrUtils.isFullkana("あいうえお")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック4() {
        assertThat(StrUtils.isFullkana("12345")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック5() {
        assertThat(StrUtils.isFullkana("#-?!")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック6() {
        assertThat(StrUtils.isFullkana("アイうえお")).isEqualTo(false);
    }

    @Test
    final void dateUtils_isFullkana_文字列チェック7() {
        assertThat(StrUtils.isFullkana("アイウエオ")).isEqualTo(true);
    }

    //
    // getLatinFromFullkana
    //
    @Test
    final void dateUtils_getLatinFromFullkana_null入力チェック1() {
        assertThat(StrUtils.getLatinFromFullkana(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_getLatinFromFullkana_文字列チェック1() {
        assertThat(StrUtils.getLatinFromFullkana("")).isEqualTo("");
    }

    @Test
    final void dateUtils_getLatinFromFullkana_文字列チェック2() {
        assertThat(StrUtils.getLatinFromFullkana("アイうえお")).isEqualTo("");
    }

    @Test
    final void dateUtils_getLatinFromFullkana_文字列チェック3() {
        assertThat(StrUtils.getLatinFromFullkana("アイウエオ")).isEqualTo("AIUEO");
    }

    //
    // gethalfwidthdigitfromfullwidthdigit
    //
    @Test
    final void dateUtils_gethalfwidthdigitfromfullwidthdigit_null入力チェック1() {
        assertThat(StrUtils.gethalfwidthdigitfromfullwidthdigit(null)).isEqualTo(null);
    }

    @Test
    final void dateUtils_gethalfwidthdigitfromfullwidthdigit_文字列チェック1() {
        assertThat(StrUtils.gethalfwidthdigitfromfullwidthdigit("")).isEqualTo("");
    }

    @Test
    final void dateUtils_gethalfwidthdigitfromfullwidthdigit_文字列チェック2() {
        assertThat(StrUtils.gethalfwidthdigitfromfullwidthdigit("ab345")).isEqualTo("ab345");
    }

    @Test
    final void dateUtils_gethalfwidthdigitfromfullwidthdigit_文字列チェック3() {
        assertThat(StrUtils.gethalfwidthdigitfromfullwidthdigit("１２３あ")).isEqualTo("123あ");
    }

    @Test
    final void dateUtils_gethalfwidthdigitfromfullwidthdigit_文字列チェック4() {
        assertThat(StrUtils.gethalfwidthdigitfromfullwidthdigit("１２３")).isEqualTo("123");
    }
}
