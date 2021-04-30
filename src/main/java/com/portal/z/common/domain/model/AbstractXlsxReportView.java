package com.portal.z.common.domain.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.exception.ApplicationException;

/**
 * Excelレポート出力用<BR>
 * <BR>
 * 【概要】<BR>
 * createWorkbookをOverrideしています。<BR>
 * 作成するworkbookは、レポート作成用のエクセルテンプレートファイルを使います。<BR>
 * <BR>
 * 【エクセルテンプレートファイルの指定方法】<BR>
 * controllerでmodelパラメータ"template"にファイル名を登録しておきます。<BR>
 * 登録しない場合は、白紙のworkbook（XSSFWorkbook()）が返されます。<BR>
 * <BR>
 * 【補足】<BR>
 * ・テンプレートファイルの置き場所はEXCEL_TEMPLATEで設定したフォルダです。<BR>
 * ・指定したテンプレートファイルが無い場合は、ApplicationExceptionをthrowします。
 *
 */
public abstract class AbstractXlsxReportView extends AbstractXlsxView {

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {

        // テンプレートを指定されていなければ、白紙のworkbookを返す。
        if (model.get("template") == null || model.get("template").toString().isEmpty()) {
            return new XSSFWorkbook();
        }

        // 作成するエクセルファイルの名称を定義
        File excelTemplateFile = new File(Constants.EXCEL_TEMPLATE + model.get("template"));

        try (InputStream is = new ByteArrayInputStream(Files.readAllBytes(Paths.get(excelTemplateFile.toString())))) {
            // エクセルテンプレートを使ってエクセルブックを作る。
            return WorkbookFactory.create(is);
        } catch (IOException | EncryptedDocumentException e) {
            // テンプレートが開けなかったとき。
            throw new ApplicationException(null, Constants.NOT_FOUND_TEMPLATE, e);
        }
    }
}
