package com.portal.z.user.domain.model;

import com.portal.z.common.domain.model.AbstractCsvView;
import com.portal.z.common.domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.util.List;
import java.util.Map;

/**
 * CSV出力用
 *
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class UserListCsvView extends AbstractCsvView {

    // ヘッダーを和名で設定
    String[] Header_J = { "ユーザID", "ユーザ有効期限", "パスワード有効期限", "ログイン失敗回数", "ロック状態", "社員CD", "社員名漢字（姓）", "有効フラグ", "作成者", "作成日時", "更新者",
            "更新日時" };

    // 出力する項目を設定
    String[] Header = { "user_id", "user_due_date", "pass_update", "login_miss_times", "lock_flg", "employee_cd", "employee_name1_last",
            "enabled_flg", "insert_user", "insert_date", "update_user", "update_date" };

    @Override
    protected void addResponseHeader(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) {

        // 出力するファイル名を定義
        String OutputFilrName = "userlist.csv";

        response.setHeader("Content-Disposition", "attachment; filename=" + OutputFilrName);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 出力するデータを受け取る。
        List<User> userList = (List<User>) model.get("userList");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(Header_J);

        for (User user : userList) {
            csvWriter.write(user, Header);
        }
        csvWriter.close();
    }
}
