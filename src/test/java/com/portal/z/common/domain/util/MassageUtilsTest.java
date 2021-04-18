package com.portal.z.common.domain.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * com.portal.z.common.domain.util.MassageUtils のテストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:postgresql://localhost:5432/portal",
        "DATASOURCE_PASSWORD=admin", "DATASOURCE_USERNAME=postgres" })
class MessageUtilsTest {

    @Autowired
    private MassageUtils massageUtils;

    /*
     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
     * DATASOURCE_PASSWORD admin
     */
    //
    // getMsg
    //
    @Test
    final void utility_getMsg_メッセージID無し() {
        assertThat(massageUtils.getMsg("999", null)).isEqualTo("[999]：エラーメッセージが登録されていません。");
    }

    @Test
    final void utility_getMsg_RoleNameNotFoundAtEnvTable() {
        assertThat(massageUtils.getMsg("e.co.fw.3.000", new String[] { "TEST" }))
                .isEqualTo("[e.co.fw.3.000]：想定外のエラーが発生しました。 : TEST");
    }
}
