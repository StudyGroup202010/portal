package com.portal.z.common.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * com.portal.z.common.domain.service.SqlShareService のテストクラス
 */
@SpringBootTest
@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:mysql://localhost:3306/portal",
"DATASOURCE_PASSWORD=Portaladmin", "DATASOURCE_USERNAME=root" })
class SqlShareServiceTest {

    @Autowired
    private SqlSharedService sqlSharedService;

    /*
     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
     * DATASOURCE_PASSWORD admin
     */

    //
    // getstrcolumnlength
    //
    @Test
    final void sqlShareService_getstrcolumnlength_null入力チェック1() {
        assertThat(sqlSharedService.getstrcolumnlength(null, null)).isEqualTo(0);
    }

    @Test
    final void sqlShareService_getstrcolumnlength_null入力チェック2() {
        assertThat(sqlSharedService.getstrcolumnlength(null, "user_id")).isEqualTo(0);
    }

    @Test
    final void sqlShareService_getstrcolumnlength_null入力チェック3() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", null)).isEqualTo(0);
    }

    @Test
    final void sqlShareService_getstrcolumnlength_未存在テーブルチェック() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user1", "user_id")).isEqualTo(0);
    }

    @Test
    final void sqlShareService_getstrcolumnlength_未存在項目チェック() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "user_id1")).isEqualTo(0);
    }
    
    @Test
    final void sqlShareService_getstrcolumnlength_項目チェック文字列() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "user_id")).isEqualTo(50);
    }
    
    @Test
    final void sqlShareService_getstrcolumnlength_項目チェック数字1() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "login_miss_times")).isEqualTo(0);
    }
    
    @Test
    final void sqlShareService_getstrcolumnlength_項目チェック論理() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "enabled_flg")).isEqualTo(0);
    }
    
    @Test
    final void sqlShareService_getstrcolumnlength_項目チェック日付() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "pass_update")).isEqualTo(0);
    }
    
    @Test
    final void sqlShareService_getstrcolumnlength_項目チェック日時() {
        assertThat(sqlSharedService.getstrcolumnlength("zm001_user", "insert_date")).isEqualTo(0);
    }
}
