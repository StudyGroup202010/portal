package com.portal.a.organization.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.a.common.domain.model.Organization;
import com.portal.z.common.domain.util.DateUtils;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class OrganizationListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("組織マスタ");

        // Controllerから受け取った値をセット
        List<Organization> organizationList = (List<Organization>) model.get("organizationList");
        int organizationListCount = (int) model.get("organizationListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("組織CD");
        row.createCell(1).setCellValue("組織名");
        row.createCell(2).setCellValue("会社名");
        row.createCell(3).setCellValue("開始年月");
        row.createCell(4).setCellValue("最終年月");
        row.createCell(5).setCellValue("備考");

        row.createCell(6).setCellValue("作成者");
        row.createCell(7).setCellValue("作成日時");
        row.createCell(8).setCellValue("更新者");
        row.createCell(9).setCellValue("更新日時");

        // 指定したシートにデータをセット
        for (int i = 0; i < organizationListCount; i++) {

            // 行を追加
            row = sheet.createRow(i + 1);
            // 組織CD
            row.createCell(0).setCellValue(organizationList.get(i).getOrganization_cd());
            // 組織名
            row.createCell(1).setCellValue(organizationList.get(i).getOrganization_name());
            // 会社名
            row.createCell(2).setCellValue(organizationList.get(i).getCompany_cd());
            // 開始年月
            row.createCell(3).setCellValue(organizationList.get(i).getStart_yearmonth());
            // 最終年月
            row.createCell(4).setCellValue(organizationList.get(i).getEnd_yearmonth());
            // 備考
            row.createCell(5).setCellValue(organizationList.get(i).getBiko());

            // 作成者
            row.createCell(6).setCellValue(organizationList.get(i).getInsert_user());
            // 作成日時
            String Insert_date = DateUtils
                    .getStringFromDateTimeFormat1(organizationList.get(i).getInsert_date().toLocalDateTime());
            row.createCell(7).setCellValue(Insert_date);
            // 更新者
            row.createCell(8).setCellValue(organizationList.get(i).getUpdate_user());
            // 更新日時
            if (organizationList.get(i).getUpdate_date() != null) {
                String Update_date = DateUtils
                        .getStringFromDateTimeFormat1(organizationList.get(i).getUpdate_date().toLocalDateTime());
                row.createCell(9).setCellValue(Update_date);
            }
        }
        // カラム幅を自動調整
        for (int i = 0; i <= organizationListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}