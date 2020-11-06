package com.portal.z.user.domain.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.RichTextString;
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
public class UserListXlsxView extends AbstractXlsxView {

    	@Override
    	protected void buildExcelDocument(Map<String, Object> model
    			, Workbook workbook, HttpServletRequest request,
    			HttpServletResponse response) throws Exception {
    		
    		System.out.println("１１１" + model.keySet());
    		System.out.println("１１１１" + model.values());
    		
    		List<User> userList = (List<User>) model.get("userList");
    		//List<User> userList = (List<User>) model.keySet();
    		int userListCount = (int)model.get("userListCount");
    		
    		System.out.println("１１２");
    		
    		Sheet sheet = workbook.createSheet("Sheet1");
    		
    	    // create header
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("user_id");
            row.createCell(1).setCellValue("user_due_date");
            row.createCell(2).setCellValue("password");
            row.createCell(3).setCellValue("pass_update");
            row.createCell(4).setCellValue("login_miss_times");
            row.createCell(5).setCellValue("lock_flg");
            row.createCell(6).setCellValue("enabled_flg");
            row.createCell(7).setCellValue("insert_user");
            row.createCell(8).setCellValue("insert_date");
            row.createCell(9).setCellValue("update_user");
            row.createCell(10).setCellValue("update_date");
            
            System.out.println("１１３" + userList.toString()  );
            System.out.println("１１４" + userList.get(1) );
           // System.out.println("１１４" + userList. );
            

            // create body
            //
            //　ここで、modelの値をうまく渡せない。。。
            //
            for (int i=0; i<userListCount ; i++) {
                row = sheet.createRow(i+1);
                  row.createCell(0).setCellValue((RichTextString) userList.get(i));
//                row.createCell(0).setCellValue(((User) userList).getUser_id() );
//                row.createCell(1).setCellValue(((User) userList).getUser_due_date() );
//                row.createCell(2).setCellValue(((User) userList).getPassword());
//                row.createCell(3).setCellValue(((User) userList).getPass_update() );
//                row.createCell(4).setCellValue(userList.getLogin_miss_times());
//                row.createCell(5).setCellValue(userList.isLock_flg() );
//                row.createCell(6).setCellValue(userList.isEnabled_flg());
//                row.createCell(7).setCellValue(userList.getInsert_user());
//                row.createCell(8).setCellValue(userList.getInsert_date());
//                row.createCell(9).setCellValue(userList.getUpdate_user());
//                row.createCell(10).setCellValue(userList.getUpdate_date());
            }
            
            // enable auto filter
//            sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 4));
            
            // adjust column width
//            for (int i=0; i<5; i++) {
//                sheet.autoSizeColumn(i);
//            }
    		
    		
    		
    		
    		
    		//受け取ったデータをエクセルシートに置いていく処理
//    		Row row = sheet.createRow(0);
//    		Cell cell = row.createCell(0);
//    		cell.setCellValue((String)model.toString());
    	}

	
}