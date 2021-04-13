//package com.portal.z.common.domain.util;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
///**
// * com.portal.z.common.domain.util.ExcelUtils のテストクラス
// */
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@TestPropertySource(properties = { "DATASOURCE_URL= jdbc:postgresql://localhost:5432/portal",
//        "DATASOURCE_PASSWORD=admin", "DATASOURCE_USERNAME=postgres" })
//class ExcelUtilsTest {
//    /*
//     * 注意： 環境変数を変更している場合、@TestPropertySourceの設定を変更してください。 【標準の設定】 DATASOURCE_URL
//     * jdbc:postgresql://localhost:5432/SAMPLE DATASOURCE_USERNAME postgres
//     * DATASOURCE_PASSWORD admin
//     */
//    //
//    // getMsg
//    //
////    @Test
////    final void utility_getMsg_メッセージID無し() throws IOException {
////        
////        String tempName = "userList.xlsx";
////        File excelTemplateFile = new File(Constants.EXCEL_TEMPLATE + tempName);
////        InputStream is = new ByteArrayInputStream(Files.readAllBytes(Paths.get(excelTemplateFile.toString())));
////        Workbook workbook = null;
////        workbook = WorkbookFactory.create(is);
////        Sheet sheet = workbook.getSheetAt(0);
////        sheet.createRow(0);
////        assertThat(ExcelUtils.setCell(sheet,1,1)).isEqualTo("[999]：エラーメッセージが登録されていません。");    
////    }
//}
