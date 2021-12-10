package com.portal.b.skill.domain.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.portal.a.common.domain.model.Employee;
import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Empcertification;
import com.portal.b.common.domain.model.Skill;
import com.portal.z.common.domain.model.AbstractXlsxReportView;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.StrUtils;

/**
 * 技術者業務経歴書出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class SkillReportXlsxView extends AbstractXlsxReportView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.getSheet("経歴書");
        if (sheet == null) {
            // 指定したシートが無かったときは何もデータを出力しないで終了。
            return;
        }

        Row row_3 = sheet.getRow(2); // フリガナ、住所
        Row row_4 = sheet.getRow(3); // 氏名、性別
        Row row_5 = sheet.getRow(4); // 生年月日、最寄り駅
        Row row_6 = sheet.getRow(5); // 最終学歴、特記事項
        Row row_7 = sheet.getRow(6); // 学科
        Row row_8 = sheet.getRow(7); // 卒業年月
        Row row_9 = sheet.getRow(8); // 社員資格
        Row row_115 = sheet.getRow(114); // 備考

        // Controllerから受け取った社員マスタ情報をセット
        Employee employeeDetail = (Employee) model.get("employeeDetail");

        // フリガナ
        // 個人情報保護のため出力しない

        // 氏名
        // 氏名をイニシャルに変換する
        String last_name = StrUtils.getSubstring(StrUtils.getLatinFromFullkana(employeeDetail.getEmployee_name2_last()),
                0, 1);
        String first_name = StrUtils
                .getSubstring(StrUtils.getLatinFromFullkana(employeeDetail.getEmployee_name2_first()), 0, 1);

        String initial_name = "";
        if (last_name != null && !last_name.isEmpty() && first_name != null && !first_name.isEmpty()) {
            initial_name = String.join(".", last_name, first_name);
        }
        row_4.getCell(3).setCellValue(initial_name);

        // 性別
        String gender = employeeDetail.getGender_kbn();
        if (Constants.GENDER_KBN_MAN.equals(gender)) {
            gender = "男";
        } else if (Constants.GENDER_KBN_WOMAN.equals(gender)) {
            gender = "女";
        } else {
            gender = "";
        }
        row_4.getCell(9).setCellValue(gender);

        // 住所
        String address = employeeDetail.getPref_name1() + ' ' + employeeDetail.getCity_name1() + ' '
                + employeeDetail.getStreetaddress1();
        row_3.getCell(12).setCellValue(address);

        // 生年月日
        row_5.getCell(3).setCellValue(employeeDetail.getBirthday());

        // 最寄り駅
        row_5.getCell(12).setCellValue(employeeDetail.getNearest_station_name());

        // Controllerから受け取ったスキル情報をセット
        Skill skillDetail = (Skill) model.get("skillDetail");

        // 最終学歴
        row_6.getCell(6).setCellValue(skillDetail.getFinal_education());

        // 学科
        row_7.getCell(6).setCellValue(skillDetail.getDepartment());

        // 卒業年月
        row_8.getCell(6).setCellValue(DateUtils.getDateFromStringmonth(skillDetail.getGraduation_date()));

        // 特記事項
        row_6.getCell(12).setCellValue(skillDetail.getNotices());

        // 社員資格
        String empcertification = "";
        String commma = ""; // 仕切り文字をセット

        // Controllerから受け取った社員資格情報をセット
        List<Empcertification> empcertificationList = (List<Empcertification>) model.get("empcertificationLisｔ");
        for (int i = 0; i < empcertificationList.size(); i++) {
            // セットする社員資格情報
            Empcertification certification = empcertificationList.get(i);
            if (certification.getCareercertification_id() != null
                    && !certification.getCareercertification_id().isEmpty()) {
                empcertification = empcertification.concat(commma + certification.getCertification_name());
                commma = "、";
            }
        }
        row_9.getCell(3).setCellValue(empcertification);

        // 備考
        row_115.getCell(5).setCellValue(skillDetail.getBiko());

        // Controllerから受け取った業務経歴情報をセット
        List<Career> careerList = (List<Career>) model.get("careerList");

        Row row = null;
        Career career = null;
        for (int i = 0; i < careerList.size(); i++) {
            // 業務経歴を指定行から出力する。
            // 開始年月と終了年月が２行になっているのでこのような指定方法になっています。
            row = sheet.getRow(Constants.SKILLREPORT_CAREER_START + (i * 2 - 1));
            row.setHeightInPoints(Constants.SKILLREPORT_CAREER_HEIGHT);

            // セットする業務経歴情報
            career = careerList.get(i);

            // 表示順
            row.getCell(0).setCellValue(career.getDisp_order());
            // 開始年月
            row.getCell(1).setCellValue(DateUtils.getDateFromStringmonth(career.getStart_yearmonth()).atStartOfDay());
            // 業務内容
            row.getCell(6).setCellValue(career.getBusiness_content());
            // 機種／OS
            row.getCell(12).setCellValue(career.getTechnology_OS());
            // 言語／ﾂｰﾙ
            row.getCell(14).setCellValue(career.getTechnology_Lang());
            // DB／DC
            row.getCell(16).setCellValue(career.getTechnology_DB());
            // 工程名略
            row.getCell(18).setCellValue(career.getProcess_name_short());
            // 終了年月
            row = sheet.getRow(Constants.SKILLREPORT_CAREER_START + (i * 2));
            row.setHeightInPoints(Constants.SKILLREPORT_CAREER_HEIGHT);

            if (career.getEnd_yearmonth().length() != 0) {
                row.getCell(1).setCellValue(DateUtils.getDateFromStringmonth(career.getEnd_yearmonth()).atStartOfDay());
            } else {
                // 終了年月が未入力の時
                row.getCell(1).setCellValue(Constants.SKILLREPORT_END_YEARMONTH);
            }
        }

        // 業務経歴書に出力した行以降を非表示にする。（ただし最低でもSKILLREPORT_CAREER_DEFAULT_COUNTの行数は出力する）
        int careerListsize = Constants.SKILLREPORT_CAREER_DEFAULT_COUNT;
        if (Constants.SKILLREPORT_CAREER_DEFAULT_COUNT < careerList.size()) {
            careerListsize = careerList.size();
        }

        for (int j = careerListsize; j < Constants.SKILLREPORT_CAREER_MAX_COUNT; j++) {
            row = sheet.getRow(Constants.SKILLREPORT_CAREER_START + (j * 2 - 1));
            row.setZeroHeight(true);
            row = sheet.getRow(Constants.SKILLREPORT_CAREER_START + (j * 2));
            row.setZeroHeight(true);
        }

        // 業務経歴書のファイル名を作成する。
        String fileName = Constants.SKILLREPORT_NAME + "_" + employeeDetail.getEmployee_cd() + "_"
                + employeeDetail.getEmployee_name1_last() + employeeDetail.getEmployee_name1_first() + "_"
                + DateUtils.getStringFromDateTimeFormat2(LocalDateTime.now()) + ".xlsx";
        response.setHeader("Content-Disposition",
                "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}