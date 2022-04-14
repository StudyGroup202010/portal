package com.portal.a.kbn.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.a.common.domain.model.Kbn;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class KbnListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("汎用区分マスタ");

        // Controllerから受け取った値をセット
        List<Kbn> kbnList = (List<Kbn>) model.get("kbnList");
        int kbnListCount = (int) model.get("kbnListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("区分ID");
        row.createCell(1).setCellValue("区分値");
        row.createCell(2).setCellValue("区分名");
        row.createCell(3).setCellValue("備考");
        row.createCell(4).setCellValue("有効フラグ");
        row.createCell(5).setCellValue("表示順（ＮＯ）");

        // 指定したシートにデータをセット
        for (int i = 0; i < kbnListCount; i++) {
            int j=0;
            // 行を追加
            row = sheet.createRow(i + 1);
            // 区分ID
            row.createCell(j++).setCellValue(kbnList.get(i).getKbn_id());
            // 区分値
            row.createCell(j++).setCellValue(kbnList.get(i).getKbn_name());
            // 区分名
            row.createCell(j++).setCellValue(kbnList.get(i).getKbn_name());
            // 備考
            row.createCell(j++).setCellValue(kbnList.get(i).getBiko());
            // 有効フラグ
            row.createCell(j++).setCellValue(kbnList.get(i).getStr_enabled_flg());
            // 表示順
            row.createCell(j++).setCellValue(kbnList.get(i).getDisp_order());
        }
        // カラム幅を自動調整
        for (int i = 0; i <= kbnListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}