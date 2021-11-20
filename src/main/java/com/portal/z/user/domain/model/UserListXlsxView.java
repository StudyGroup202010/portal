package com.portal.z.user.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.util.DateUtils;

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class UserListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.createSheet("ユーザマスタ");

        // Controllerから受け取った値をセット
        List<User> userList = (List<User>) model.get("userList");
        int userListCount = (int) model.get("userListCount");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ユーザID");
        row.createCell(1).setCellValue("ユーザ有効期限");
        row.createCell(2).setCellValue("パスワード有効期限");
        row.createCell(3).setCellValue("ログイン失敗回数");
        row.createCell(4).setCellValue("ロック状態");
        row.createCell(5).setCellValue("社員CD");
        row.createCell(6).setCellValue("社員名");
        row.createCell(7).setCellValue("有効フラグ");
        row.createCell(8).setCellValue("作成者");
        row.createCell(9).setCellValue("作成日時");
        row.createCell(10).setCellValue("更新者");
        row.createCell(11).setCellValue("更新日時");

        // 指定したシートにデータをセット
        for (int i = 0; i < userListCount; i++) {

            // 行を追加
            row = sheet.createRow(i + 1);
            // ユーザID
            row.createCell(0).setCellValue(userList.get(i).getUser_id());
            // ユーザ有効期限
            String User_due_date = DateUtils.getStringFromDateFormat(userList.get(i).getUser_due_date().toLocalDate());
            row.createCell(1).setCellValue(User_due_date);
            // パスワード有効期限
            String Pass_update = DateUtils.getStringFromDateFormat(userList.get(i).getPass_update().toLocalDate());
            row.createCell(2).setCellValue(Pass_update);
            // ログイン失敗回数
            row.createCell(3).setCellValue(userList.get(i).getLogin_miss_times());
            // ロック状態
            row.createCell(4).setCellValue(userList.get(i).isLock_flg());
            // 社員CD
            row.createCell(5).setCellValue(userList.get(i).getEmployee_cd());
            // 社員名漢字（姓）
            row.createCell(6).setCellValue(userList.get(i).getEmployee_name1_last());
            // 有効フラグ
            row.createCell(7).setCellValue(userList.get(i).isEnabled_flg());
            // 作成者
            row.createCell(8).setCellValue(userList.get(i).getInsert_user());
            // 作成日時
            String Insert_date = DateUtils
                    .getStringFromDateTimeFormat1(userList.get(i).getInsert_date().toLocalDateTime());
            row.createCell(9).setCellValue(Insert_date);
            // 更新者
            row.createCell(10).setCellValue(userList.get(i).getUpdate_user());
            // 更新日時
            if (userList.get(i).getUpdate_date() != null) {
                String Update_date = DateUtils
                        .getStringFromDateTimeFormat1(userList.get(i).getUpdate_date().toLocalDateTime());
                row.createCell(11).setCellValue(Update_date);
            }
        }
        // カラム幅を自動調整
        for (int i = 0; i <= userListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}