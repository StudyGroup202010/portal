package com.portal.a.company.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.a.common.domain.model.Company;
import com.portal.z.common.domain.util.DateUtils;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class CompanyListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("会社マスタ");

        // Controllerから受け取った値をセット
        List<Company> companyList = (List<Company>) model.get("companyList");
        int companyListCount = (int) model.get("companyListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("会社CD");
        row.createCell(1).setCellValue("会社名");
        row.createCell(2).setCellValue("備考");

        row.createCell(3).setCellValue("作成者");
        row.createCell(4).setCellValue("作成日時");
        row.createCell(5).setCellValue("更新者");
        row.createCell(6).setCellValue("更新日時");

        // 指定したシートにデータをセット
        for (int i = 0; i < companyListCount; i++) {

            // 行を追加
            row = sheet.createRow(i + 1);
            // 会社CD
            row.createCell(0).setCellValue(companyList.get(i).getCompany_cd());
            // 会社名
            row.createCell(1).setCellValue(companyList.get(i).getCompany_name());
            // 備考
            row.createCell(2).setCellValue(companyList.get(i).getBiko());

            // 作成者
            row.createCell(3).setCellValue(companyList.get(i).getInsert_user());
            // 作成日時
            String Insert_date = DateUtils
                    .getStringFromDateTimeFormat1(companyList.get(i).getInsert_date().toLocalDateTime());
            row.createCell(4).setCellValue(Insert_date);
            // 更新者
            row.createCell(5).setCellValue(companyList.get(i).getUpdate_user());
            // 更新日時
            if (companyList.get(i).getUpdate_date() != null) {
                String Update_date = DateUtils
                        .getStringFromDateTimeFormat1(companyList.get(i).getUpdate_date().toLocalDateTime());
                row.createCell(6).setCellValue(Update_date);
            }
        }
        // カラム幅を自動調整
        for (int i = 0; i <= companyListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}