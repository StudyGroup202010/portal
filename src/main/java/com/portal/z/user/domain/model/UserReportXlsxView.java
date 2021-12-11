package com.portal.z.user.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.portal.z.common.domain.model.AbstractXlsxReportView;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.util.DateUtils;

/**
 * 帳票出力用.
 */
@Component
@SuppressWarnings("unchecked") // このアノテーションつけないと「型の安全性: Object から ～ への未検査キャスト」が出る
public class UserReportXlsxView extends AbstractXlsxReportView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // データを出力するシートを指定
        Sheet sheet = workbook.getSheet("ユーザー一覧");
        if (sheet == null) {
            // 指定したシートが無かったとき。
            sheet = workbook.createSheet();
        }

        // セルにセットする罫線を設定
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setBorderRight(BorderStyle.THIN);
        cellstyle.setBorderLeft(BorderStyle.THIN);
        cellstyle.setBorderTop(BorderStyle.THIN);
        cellstyle.setBorderBottom(BorderStyle.THIN);

        // Controllerから受け取った値をセット
        List<User> userList = (List<User>) model.get("userList");
        int userListCount = (int) model.get("userListCount");

        // 指定したシートにデータをセット
        for (int i = 0; i < userListCount; i++) {

            // 行を追加（エクセルテンプレートに合わせて設定する。
            Row row = sheet.createRow(i + 3);

            // ユーザID
            row.createCell(0).setCellStyle(cellstyle);
            row.getCell(0).setCellValue(userList.get(i).getUser_id());

            // ユーザ有効期限
            row.createCell(1).setCellStyle(cellstyle);
            String User_due_date = DateUtils.getStringFromDate(userList.get(i).getUser_due_date().toLocalDate());
            row.getCell(1).setCellValue(User_due_date);

            // パスワード有効期限
            row.createCell(2).setCellStyle(cellstyle);
            String Pass_update = DateUtils.getStringFromDate(userList.get(i).getPass_update().toLocalDate());
            row.getCell(2).setCellValue(Pass_update);

            // ログイン失敗回数
            row.createCell(3).setCellStyle(cellstyle);
            row.getCell(3).setCellValue(userList.get(i).getLogin_miss_times());

            // ロック状態
            row.createCell(4).setCellStyle(cellstyle);
            row.getCell(4).setCellValue(userList.get(i).isLock_flg());

            // 社員CD
            row.createCell(5).setCellStyle(cellstyle);
            row.getCell(5).setCellValue(userList.get(i).getEmployee_cd());

            // 社員名漢字（姓）
            row.createCell(6).setCellStyle(cellstyle);
            row.getCell(6).setCellValue(userList.get(i).getEmployee_name1_last());

            // 有効フラグ
            row.createCell(7).setCellStyle(cellstyle);
            row.getCell(7).setCellValue(userList.get(i).isEnabled_flg());

            // 作成者
            row.createCell(8).setCellStyle(cellstyle);
            row.getCell(8).setCellValue(userList.get(i).getInsert_user());

            // 作成日時
            row.createCell(9).setCellStyle(cellstyle);
            String Insert_date = DateUtils.getStringFromDateTime(userList.get(i).getInsert_date().toLocalDateTime());
            row.getCell(9).setCellValue(Insert_date);

            // 更新者
            row.createCell(10).setCellStyle(cellstyle);
            row.getCell(10).setCellValue(userList.get(i).getUpdate_user());

            // 更新日時
            row.createCell(11).setCellStyle(cellstyle);
            if (userList.get(i).getUpdate_date() != null) {
                String Update_date = DateUtils
                        .getStringFromDateTime(userList.get(i).getUpdate_date().toLocalDateTime());
                row.getCell(11).setCellValue(Update_date);
            }
        }
        // カラム幅を自動調整
        for (int i = 0; i <= userListCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}