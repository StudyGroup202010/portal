package com.portal.z.user.domain.model;

import java.text.SimpleDateFormat;
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

/**
 * ＥＸＣＥＬ出力用.
 */
@Component
@SuppressWarnings("unchecked")  // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class UserListXlsxView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model
            , Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Controllerから受け取った値をセット
        List<User> userList = (List<User>) model.get("userList");
        int userListCount = (int)model.get("userListCount");

        Sheet sheet = workbook.createSheet("Sheet1");

        // 列名をセット
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ユーザID");
        row.createCell(1).setCellValue("ユーザ有効期限");
        row.createCell(2).setCellValue("パスワード有効期限");
        row.createCell(3).setCellValue("ログイン失敗回数");
        row.createCell(4).setCellValue("ロック状態");
        row.createCell(5).setCellValue("有効フラグ");
        row.createCell(6).setCellValue("作成者");
        row.createCell(7).setCellValue("作成日時");
        row.createCell(8).setCellValue("更新者");
        row.createCell(9).setCellValue("更新日時");

        // データをセット
        //ここはもう少し共通化したいな。日付変換とか。
        for (int i=0; i<userListCount ; i++) {
            row = sheet.createRow(i+1);
            //ユーザID
            row.createCell(0).setCellValue(userList.get(i).getUser_id() );
            //ユーザ有効期限
            String User_due_date  = new SimpleDateFormat("yyyy/MM/dd").format(userList.get(i).getUser_due_date());
            row.createCell(1).setCellValue(User_due_date);
            //パスワード有効期限
            String Pass_update    = new SimpleDateFormat("yyyy/MM/dd").format(userList.get(i).getPass_update());
            row.createCell(2).setCellValue(Pass_update );
            //ログイン失敗回数
            row.createCell(3).setCellValue(userList.get(i).getLogin_miss_times());
            //ロック状態
            row.createCell(4).setCellValue(userList.get(i).isLock_flg() );
            //有効フラグ
            row.createCell(5).setCellValue(userList.get(i).isEnabled_flg());
            //作成者
            row.createCell(6).setCellValue(userList.get(i).getInsert_user());
            //作成日時
            String Insert_date    = new SimpleDateFormat("yyyy/MM/dd").format(userList.get(i).getInsert_date());
            row.createCell(7).setCellValue(Insert_date);
            //更新者
            row.createCell(8).setCellValue(userList.get(i).getUpdate_user());
            //更新日時
            if (userList.get(i).getUpdate_date() != null) {
                String Update_date = new SimpleDateFormat("yyyy/MM/dd").format(userList.get(i).getUpdate_date());
                row.createCell(9).setCellValue(Update_date);
            }
        }

        // カラム幅を自動調整
        for (int i=0; i<9; i++) {
            sheet.autoSizeColumn(i);
        }
    }


}