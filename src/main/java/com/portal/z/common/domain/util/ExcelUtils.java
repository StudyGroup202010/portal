//package com.portal.z.common.domain.util;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//
///**
// * エクセル関係のユーティリティ
// *
// */
//public final class ExcelUtils {
//
//    // 他からインスタンス起動できないよう、コンストラクタをprivateで定義する。
//    private ExcelUtils() {
//
//    }
//
//    /**
//     * セルを選択する。
//     * 
//     * @param sheet      値をセットするシート
//     * @param rowNumber  行番号
//     * @param cellNumber 列番号
//     * @return createCell
//     */
//    public static Cell setCell(Sheet sheet, int rowNumber, int cellNumber) {
//        Row row = sheet.getRow(rowNumber);
//        return row.createCell(cellNumber);
//    }
//}
