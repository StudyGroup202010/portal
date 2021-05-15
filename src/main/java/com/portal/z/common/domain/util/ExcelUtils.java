package com.portal.z.common.domain.util;

import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portal.z.common.exception.ApplicationException;

/**
 * Excel関係のユーティリティ
 *
 */
@Component("ExcelUtils")
public class ExcelUtils {

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 文字列項目の値を取得する。<BR>
     * 
     * @param row          取得したいカラムの行
     * @param columnnum    取得したいカラムの位置（左から１～）
     * @param columnlength 取得したいカラムの最大桁数
     * @return 取得した文字列カラムの値<BR>
     *         以下の場合はnullになります<BR>
     *         ・rowがnull,columnnumかcolumnlengthが0のとき<BR>
     *         ・セルが生成されていないとき<BR>
     *         ・セルがブランクのとき<BR>
     * @throws ApplicationException セルに文字列以外が登録されていたとき<BR>
     *                              セルに登録されている文字列が最大桁数を超えていたとき
     */
    public String getColumnString(Row row, int columnnum, int columnlength) throws ApplicationException {

        if (row == null || columnnum == 0 || columnlength == 0) {
            return null;
        }

        Cell cell = row.getCell(columnnum - 1);
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return null;
        }

        if (cell.getCellType() != CellType.STRING) {
            // Stringではない場合はエラーとします。
            String messageKey = "e.co.fw.1.015";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        // 値を取得するときはトリムする。
        String cellstring = cell.getStringCellValue().trim();
        // 桁数チェック
        if (columnlength < StrUtils.getStrLength(cellstring)) {
            String messageKey = "e.co.fw.1.016";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey,
                            new String[] { cell.getSheet().getSheetName(), String.valueOf(cell.getRowIndex() + 1),
                                    String.valueOf(columnnum), String.valueOf(columnlength) }));
        }

        return cellstring;
    }

    /**
     * 数値項目の値を取得する。
     * 
     * @param row       取得したいカラムの行
     * @param columnnum 取得したいカラムの位置（左から１～）
     * @return 取得した数値カラムの値<BR>
     *         以下の場合はnullになります<BR>
     *         ・rowがnull,columnnumが0のとき<BR>
     *         ・セルが生成されていないとき<BR>
     *         ・セルがブランクのとき<BR>
     * @throws ApplicationException <BR>
     *                              ・セルに数値以外が登録されていたとき<BR>
     *                              ・セルに小数点以下の値が登録されていたとき<BR>
     *                              ・セルに登録されている数値がSmallint型の範囲に収まっていないとき（-32768～32767）<BR>
     *                              ※Postgresqlの仕様です。
     */
    public String getColumnSmallint(Row row, int columnnum) throws ApplicationException {

        if (row == null || columnnum == 0) {
            return null;
        }

        Cell cell = row.getCell(columnnum - 1);
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return null;
        }

        if ((cell.getCellType() != CellType.NUMERIC)) {
            // 数値ではない場合はエラーとします。
            String messageKey = "e.co.fw.1.017";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        // 取得結果はDouble型なのでintに変換します。
        int cellint = (int) cell.getNumericCellValue();

        if (cellint != cell.getNumericCellValue()) {
            // 小数点が登録されていた場合はエラーとします。
            String messageKey = "e.co.fw.1.020";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }
        // Smallint型の範囲に収まっていない場合はエラーとします。
        if (cellint < -32768 || 32767 < cellint) {
            String messageKey = "e.co.fw.1.019";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        return String.valueOf(cellint);
    }

    /**
     * 日付項目の値を取得する。
     * 
     * @param row       取得したいカラムの行
     * @param columnnum 取得したいカラムの位置（左から１～）
     * @return 取得した日付カラムの値<BR>
     *         以下の場合はnullになります<BR>
     *         ・rowがnull,columnnumが0のとき<BR>
     *         ・セルが生成されていないとき<BR>
     *         ・セルがブランクのとき<BR>
     * @throws ApplicationException <BR>
     *                              ・セルに数値以外が登録されていたとき<BR>
     *                              ・セルに日付に変換できない数値が登録されていたとき<BR>
     */
    public Date getColumnDate(Row row, int columnnum) throws ApplicationException {

        if (row == null || columnnum == 0) {
            return null;
        }

        Cell cell = row.getCell(columnnum - 1);
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return null;
        }

        // 型を確認してからisCellDateFormattedを使わないとエラーになるので、順番に値を評価します。
        if (cell.getCellType() != CellType.NUMERIC) {
            // 数値型ではない場合はエラーとします。
            String messageKey = "e.co.fw.1.018";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        if (DateUtil.isCellDateFormatted(cell) != true) {
            // 日付ではない場合はエラーとします。
            String messageKey = "e.co.fw.1.018";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        // java.sql.Date 型に変換します。
        return new Date(cell.getDateCellValue().getTime());
    }

    /**
     * 論理項目の値を取得する。
     * 
     * @param row       取得したいカラムの行
     * @param columnnum 取得したいカラムの位置（左から１～）
     * @return 取得した論理カラムの値<BR>
     *         以下の場合はnullになります<BR>
     *         ・rowがnull,columnnumが0のとき<BR>
     *         ・セルが生成されていないとき<BR>
     *         ・セルがブランクのとき<BR>
     * @throws ApplicationException <BR>
     *                              ・セルに論理値以外が登録されていたとき<BR>
     */
    public Boolean getColumnBoolean(Row row, int columnnum) throws ApplicationException {

        if (row == null || columnnum == 0) {
            return Boolean.FALSE;
        }

        Cell cell = row.getCell(columnnum - 1);
        if (cell == null) {
            return Boolean.FALSE;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return Boolean.FALSE;
        }

        if (cell.getCellType() != CellType.BOOLEAN) {
            // 論理型ではない場合はエラーとします。
            String messageKey = "e.co.fw.1.021";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { cell.getSheet().getSheetName(),
                            String.valueOf(cell.getRowIndex() + 1), String.valueOf(columnnum) }));
        }

        return cell.getBooleanCellValue();
    }
}
