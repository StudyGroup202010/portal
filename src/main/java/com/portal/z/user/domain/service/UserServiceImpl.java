package com.portal.z.user.domain.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;
import com.portal.z.common.domain.service.SqlSharedService;
import com.portal.z.common.domain.service.UserSharedService;
import com.portal.z.common.domain.util.ExcelUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.exception.ApplicationException;

/**
 * UserServiceImpl
 *
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private MassageUtils massageUtils;

    @Autowired
    private ExcelUtils excelUtils;

    @Autowired
    private SqlSharedService sqlSharedService;

    @Autowired
    private UserSharedService userSharedService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> selectMany() {
        return userMapper.selectMany();
    }

    public User selectOne(String user_id) {
        return userMapper.selectOne(user_id);
    }

    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to) {
        return userMapper.selectBy(user_id, user_due_date_from, user_due_date_to);
    }

    public boolean insertFromExcel(MultipartFile file, String SheetName)
            throws EncryptedDocumentException, IOException, ApplicationException {

        // 指定したシートを取得する。
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheet(SheetName);
        if (sheet == null) {
            // 指定したシートが無かったとき。
            String messageKey = "e.co.fw.1.011";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { SheetName }));
        }

        // ヘッダーを取得
        Row row = sheet.getRow(0);
        // ヘッダーの値をチェック
        if (checkheader(row) != true) {
            // シートに登録されたヘッダーの情報が間違っているとき
            String messageKey = "e.co.fw.1.012";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { SheetName }));
        }

        // 最終行を取得
        int lastRowNbr = sheet.getLastRowNum();
        if (lastRowNbr == 0) {
            // シートにデータが登録されていないとき
            String messageKey = "e.co.fw.1.013";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { SheetName }));
        }

        int columnnum; // 列番号
        int columnlength; // 項目の桁数
        String cellstring; // セルの値
        Date celldate;// セルの値（日付型）

        boolean result = false;

        // ユーザマスタinsert用変数
        User user = new User();

        for (int rowNbr = 1; lastRowNbr >= rowNbr; rowNbr++) {

            // 行データを読み込む
            row = sheet.getRow(rowNbr);
            if (row == null) {
                break;
            }

            // １つめの項目（ユーザーID）
            columnnum = 1;

            // 桁数取得
            columnlength = sqlSharedService.getstrcolumnlength("zm001_user", "user_id");

            // セルの値を取得
            cellstring = excelUtils.getColumnString(row, columnnum, columnlength);

            // 必須チェック
            if (cellstring == null) {
                String messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setUser_id(cellstring); // ユーザーID

            // ２つめの項目（ユーザ有効期限）
            columnnum = 2;

            // セルの値を取得
            celldate = excelUtils.getColumnDate(row, columnnum);

            // 必須チェック
            if (celldate == null) {
                String messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setUser_due_date(celldate); // ユーザ有効期限

            // パスワードは暗号化する
            String password = passwordEncoder.encode("password");
            user.setPassword(password); // パスワード
            user.setPass_update(Date.valueOf("2999-12-31")); // パスワード有効期限

            // ４つめの項目（ログイン失敗回数）
            columnnum = 4;

            // セルの値を取得
            cellstring = excelUtils.getColumnSmallint(row, columnnum);

            // 必須チェック
            if (cellstring == null) {
                String messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setLogin_miss_times(Integer.parseInt(cellstring));

            // ロック状態と有効フラグはテーブルの初期値にて設定される
            user.setLock_flg(true); // ロック状態
            user.setEnabled_flg(true); // 有効フラグ

            // ７つめの項目
            columnnum = 7;

            // 桁数取得
            columnlength = sqlSharedService.getstrcolumnlength("zm001_user", "insert_user");

            // セルの値を取得
            cellstring = excelUtils.getColumnString(row, columnnum, columnlength);

            // 必須チェック
            if (cellstring == null) {
                String messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setInsert_user(cellstring); // 作成者

            // ユーザー登録処理(user)
            result = userSharedService.insertOne(user);
        }

        return result;
    }

    /**
     * ヘッダーの設定チェック
     * 
     * @param headerRow 検査する行
     * @return true：ヘッダーが正しく登録されている。false:ヘッダーが正しく登録されていない。
     */
    private boolean checkheader(Row headerRow) {

        if (headerRow == null) {
            // ヘッダーが未登録のとき
            return false;
        }

        // ToDo チェック内容を記述
//        Cell headerCell = headerRow.getCell(0);
//        String headerStr = headerCell.getStringCellValue();

        return true;
    }
}
