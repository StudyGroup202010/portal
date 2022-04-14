package com.portal.a.kbn.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.portal.a.common.domain.model.Kbn;
import com.portal.z.common.domain.model.AbstractCsvView;

/**
 * CSV出力用
 *
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class KbnListCsvView extends AbstractCsvView {

    // ヘッダーを和名で設定
    String[] Header_J = { "区分ID", "区分値", "区分名", "備考", "有効フラグ", "表示順（ＮＯ）" };

    // 出力する項目を設定
    String[] Header = { "kbn_id", "kbn_txt", "kbn_name", "biko", "str_enabled_flg", "disp_order" };

    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {

        // 出力するファイル名を定義
        String OutputFilrName = "kbnlist.csv";

        response.setHeader("Content-Disposition", "attachment; filename=" + OutputFilrName);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 出力するデータを受け取る。
        List<Kbn> kbnList = (List<Kbn>) model.get("kbnList");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(Header_J);

        for (Kbn kbn : kbnList) {
            csvWriter.write(kbn, Header);
        }
        csvWriter.close();
    }
}
