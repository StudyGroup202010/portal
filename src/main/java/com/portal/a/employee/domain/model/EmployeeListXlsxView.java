package com.portal.a.employee.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.util.DateUtils;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class EmployeeListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("社員マスタ");

        // Controllerから受け取った値をセット
        List<Employee> employeeList = (List<Employee>) model.get("employeeList");
        int employeeListCount = (int) model.get("employeeListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("社員ID");
        row.createCell(1).setCellValue("社員CD");
        row.createCell(2).setCellValue("社員名漢字（性）");
        row.createCell(3).setCellValue("社員名漢字（名）");
        row.createCell(4).setCellValue("社員名カナ（性）");
        row.createCell(5).setCellValue("社員名カナ（名）");
        row.createCell(6).setCellValue("性別区分");
        row.createCell(7).setCellValue("郵便番号");
        row.createCell(8).setCellValue("都道府県名CD");
        row.createCell(9).setCellValue("都道府県名１");
        row.createCell(10).setCellValue("都道府県名２");
        row.createCell(11).setCellValue("市区町村名CD");
        row.createCell(12).setCellValue("市区町村名１");
        row.createCell(13).setCellValue("市区町村名２");
        row.createCell(14).setCellValue("住所１");
        row.createCell(15).setCellValue("住所２");
        row.createCell(16).setCellValue("生年月日");
        row.createCell(17).setCellValue("最寄駅コード");
        row.createCell(18).setCellValue("最寄駅名");
        row.createCell(19).setCellValue("最終学歴");
        row.createCell(20).setCellValue("学科");
        row.createCell(21).setCellValue("卒業年月");
        row.createCell(22).setCellValue("メールアドレス");
        row.createCell(23).setCellValue("入社日");
        row.createCell(24).setCellValue("退社日");
        row.createCell(25).setCellValue("社員属性ID");
        row.createCell(26).setCellValue("備考");
        row.createCell(27).setCellValue("作成者");
        row.createCell(28).setCellValue("作成日時");
        row.createCell(29).setCellValue("更新者");
        row.createCell(30).setCellValue("更新日時");

        // 指定したシートにデータをセット
        for (int i = 0; i < employeeListCount; i++) {

            // 行を追加
            row = sheet.createRow(i + 1);
            // 社員ID
            row.createCell(0).setCellValue(employeeList.get(i).getEmployee_id());
            // 社員CD
            row.createCell(1).setCellValue(employeeList.get(i).getEmployee_cd());
            // 社員名漢字（性）
            row.createCell(2).setCellValue(employeeList.get(i).getEmployee_name1_last());
            // 社員名漢字（名）
            row.createCell(3).setCellValue(employeeList.get(i).getEmployee_name1_first());
            // 社員名カナ（性）
            row.createCell(4).setCellValue(employeeList.get(i).getEmployee_name2_last());
            // 社員名カナ（名）
            row.createCell(5).setCellValue(employeeList.get(i).getEmployee_name2_first());
            // 性別区分
            row.createCell(6).setCellValue(employeeList.get(i).getGender_kbn());
            // 郵便番号
            row.createCell(7).setCellValue(employeeList.get(i).getPostcode());
            // 都道府県名CD
            row.createCell(8).setCellValue(employeeList.get(i).getPrefcode());
            // 都道府県名１
            row.createCell(9).setCellValue(employeeList.get(i).getPref_name1());
            // 都道府県名２
            row.createCell(10).setCellValue(employeeList.get(i).getPref_name2());
            // 市区町村名CD
            row.createCell(11).setCellValue(employeeList.get(i).getCitycode());
            // 市区町村名１
            row.createCell(12).setCellValue(employeeList.get(i).getCity_name1());
            // 市区町村名２
            row.createCell(13).setCellValue(employeeList.get(i).getCity_name2());
            // 住所１
            row.createCell(14).setCellValue(employeeList.get(i).getStreetaddress1());
            // 住所２
            row.createCell(15).setCellValue(employeeList.get(i).getStreetaddress2());
            // 生年月日
            row.createCell(16).setCellValue(employeeList.get(i).getBirthday());
            // 最寄駅コード
            row.createCell(17).setCellValue(employeeList.get(i).getNearest_station_code());
            // 最寄駅名
            row.createCell(18).setCellValue(employeeList.get(i).getNearest_station_name());
            // 最終学歴
            row.createCell(19).setCellValue(employeeList.get(i).getFinal_education());
            // 学科
            row.createCell(20).setCellValue(employeeList.get(i).getDepartment());
            // 卒業年月
            row.createCell(21).setCellValue(employeeList.get(i).getGraduation_date());
            // メールアドレス
            row.createCell(22).setCellValue(employeeList.get(i).getMail());
            // 入社日
            row.createCell(23).setCellValue(employeeList.get(i).getJoined_date());
            // 退社日
            row.createCell(24).setCellValue(employeeList.get(i).getLeave_date());
            // 社員属性
            row.createCell(25).setCellValue(employeeList.get(i).getEmployeeattribute_id());
            // 備考
            row.createCell(26).setCellValue(employeeList.get(i).getBiko());
            // 作成者
            row.createCell(27).setCellValue(employeeList.get(i).getInsert_user());
            // 作成日時
            String Insert_date = DateUtils
                    .getStringFromDateTimeFormat(employeeList.get(i).getInsert_date().toLocalDateTime());
            row.createCell(28).setCellValue(Insert_date);
            // 更新者
            row.createCell(29).setCellValue(employeeList.get(i).getUpdate_user());
            // 更新日時
            if (employeeList.get(i).getUpdate_date() != null) {
                String Update_date = DateUtils
                        .getStringFromDateTimeFormat(employeeList.get(i).getUpdate_date().toLocalDateTime());
                row.createCell(30).setCellValue(Update_date);
            }
        }
        // カラム幅を自動調整
        for (int i = 0; i < 30; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}