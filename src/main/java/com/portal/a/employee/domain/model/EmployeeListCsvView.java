package com.portal.a.employee.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.model.AbstractCsvView;

/**
 * CSV出力用
 *
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class EmployeeListCsvView extends AbstractCsvView {

    // ヘッダーを和名で設定
	String[] Header_J = { "社員CD", "社員名漢字（姓）", "社員名漢字（名）", "社員名カナ（姓）", "社員名カナ（名）", "性別区分", "郵便番号", "都道府県名CD", "都道府県名１",
			"都道府県名２", "市区町村名CD", "市区町村名１", "市区町村名２", "住所１", "住所２", "生年月日", "最寄駅コード", "最寄駅名", "メールアドレス", "入社日", "退社日",
			"社員属性ID", "備考", "作成者", "作成日時", "更新者", "更新日時" };

	// 出力する項目を設定
	String[] Header = { "employee_cd", "employee_name1_last", "employee_name1_first", "employee_name2_last",
			"employee_name2_first", "gender_kbn", "postcode", "prefcode", "pref_name1", "pref_name2", "citycode",
			"city_name1", "city_name2", "streetaddress1", "streetaddress2", "birthday", "nearest_station_code",
			"nearest_station_name", "mail", "joined_date", "leave_date", "employeeattribute_id", "biko", "insert_user",
			"insert_date", "update_user", "update_date" };

    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {

        // 出力するファイル名を定義
        String OutputFilrName = "employeelist.csv";

        response.setHeader("Content-Disposition", "attachment; filename=" + OutputFilrName);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 出力するデータを受け取る。
        List<Employee> employeeList = (List<Employee>) model.get("employeeList");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(Header_J);

        for (Employee employee : employeeList) {
            csvWriter.write(employee, Header);
        }
        csvWriter.close();
    }
}
