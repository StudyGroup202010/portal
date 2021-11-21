package com.portal.b.skill.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.b.common.domain.model.Skill;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class SkillListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("スキル情報");

        // Controllerから受け取った値をセット
        List<Skill> skillList = (List<Skill>) model.get("skillList");
        int skillListCount = (int) model.get("skillListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("社員CD");
        row.createCell(1).setCellValue("姓");
        row.createCell(2).setCellValue("名");
        row.createCell(3).setCellValue("最終学歴");
        row.createCell(4).setCellValue("学科");
        row.createCell(5).setCellValue("卒業年月");
        row.createCell(6).setCellValue("特記事項");
        row.createCell(7).setCellValue("資格");
        row.createCell(8).setCellValue("備考");

        row.createCell(9).setCellValue("表示順");
        row.createCell(10).setCellValue("開始年月");
        row.createCell(11).setCellValue("終了年月");
        row.createCell(12).setCellValue("業務内容");
        row.createCell(13).setCellValue("業務経歴備考");

        row.createCell(14).setCellValue("開発言語");
        row.createCell(15).setCellValue("OS");
        row.createCell(16).setCellValue("DB");
        row.createCell(17).setCellValue("工程");

        // 指定したシートにデータをセット
        for (int i = 0; i < skillListCount; i++) {

            // 行を追加
            row = sheet.createRow(i + 1);
            // 社員CD
            row.createCell(0).setCellValue(skillList.get(i).getEmployee_cd());
            // 姓
            row.createCell(1).setCellValue(skillList.get(i).getEmployee_name1_last());
            // 名
            row.createCell(2).setCellValue(skillList.get(i).getEmployee_name1_first());
            // 最終学歴
            row.createCell(3).setCellValue(skillList.get(i).getFinal_education());
            // 学科
            row.createCell(4).setCellValue(skillList.get(i).getDepartment());
            // 卒業年月
            row.createCell(5).setCellValue(skillList.get(i).getGraduation_date());
            // 特記事項
            row.createCell(6).setCellValue(skillList.get(i).getNotices());
            // 資格
            row.createCell(7).setCellValue(skillList.get(i).getCertification_name());
            // 備考
            row.createCell(8).setCellValue(skillList.get(i).getBiko());

            // 表示順
            row.createCell(9).setCellValue(skillList.get(i).getDisp_order());
            // 開始年月
            row.createCell(10).setCellValue(skillList.get(i).getStart_yearmonth());
            // 終了年月
            row.createCell(11).setCellValue(skillList.get(i).getEnd_yearmonth());
            // 業務内容
            row.createCell(12).setCellValue(skillList.get(i).getBusiness_content());
            // 業務経歴備考
            row.createCell(13).setCellValue(skillList.get(i).getCareerbiko());
            // 技術名（開発言語）
            row.createCell(14).setCellValue(skillList.get(i).getTechnology_Lang());
            // 技術名（OS）
            row.createCell(15).setCellValue(skillList.get(i).getTechnology_OS());
            // 技術名（DB）
            row.createCell(16).setCellValue(skillList.get(i).getTechnology_DB());
            // 工程
            row.createCell(17).setCellValue(skillList.get(i).getProcess_name());

        }
        // カラム幅を自動調整
        for (int i = 0; i <= skillListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}