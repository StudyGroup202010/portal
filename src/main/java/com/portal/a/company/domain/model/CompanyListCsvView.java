package com.portal.a.company.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.portal.a.common.domain.model.Company;
import com.portal.z.common.domain.model.AbstractCsvView;

/**
 * CSV出力用
 *
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class CompanyListCsvView extends AbstractCsvView {

    // ヘッダーを和名で設定
    String[] Header_J = { "会社CD", "会社名", "備考", "作成者", "作成日時", "更新者", "更新日時" };

    // 出力する項目を設定
    String[] Header = { "company_cd", "company_name", "biko", "insert_user", "insert_date", "update_user",
            "update_date" };

    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {

        // 出力するファイル名を定義
        String OutputFilrName = "companylist.csv";

        response.setHeader("Content-Disposition", "attachment; filename=" + OutputFilrName);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 出力するデータを受け取る。
        List<Company> companyList = (List<Company>) model.get("companyList");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(Header_J);

        for (Company company : companyList) {
            csvWriter.write(company, Header);
        }
        csvWriter.close();
    }
}
