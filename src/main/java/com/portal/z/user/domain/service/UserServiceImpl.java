package com.portal.z.user.domain.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.repository.EmployeeMapper;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;
import com.portal.z.common.domain.service.SqlSharedService;
import com.portal.z.common.domain.service.UserSharedService;
import com.portal.z.common.domain.util.Constants;
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
    EmployeeMapper employeeMapper;

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

    public List<Employee> selectManyExceptRetireeEmployee() {
        return employeeMapper.selectMany(null);
    }

    public User selectOne(String user_id) {
        return userMapper.selectOne(user_id, null);
    }

    public User selectOneByEmployeeid(String employee_id) {
        return userMapper.selectOne(null, employee_id);
    }

    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to) {
        return userMapper.selectBy(user_id, user_due_date_from, user_due_date_to);
    }

    public boolean insertFromExcel(MultipartFile file, String SheetName)
            throws EncryptedDocumentException, IOException, ApplicationException {

        String messageKey = null;
        String message = null;

        // 指定したシートを取得する。
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheet(SheetName);
        if (sheet == null) {
            // 指定したシートが無かったとき。
            messageKey = "e.co.fw.2.004";
            message = "選択したエクセルファイルに " + SheetName + " シート";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { message }));
        }

        // ヘッダーを取得
        Row row = sheet.getRow(0);
        // ヘッダーの値をチェック
        if (checkheader(row) != true) {
            // シートに登録されたヘッダーの情報が間違っているとき
            messageKey = "e.co.fw.1.011";
            message = "選択したエクセルファイルの " + SheetName + " シート";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { message }));
        }

        // 最終行を取得
        int lastRowNbr = sheet.getLastRowNum();
        if (lastRowNbr == 0) {
            // シートにデータが登録されていないとき
            messageKey = "e.co.fw.2.004";
            message = "選択したエクセルファイルの " + SheetName + " シートにデータ";
            throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey, new String[] { message }));
        }

        int columnnum = 0; // 列番号
        int columnlength; // 項目の桁数
        String cellstring; // セルの値
        Date celldate;// セルの値（日付型）

        // ユーザマスタinsert用
        User user = new User();
        boolean result = false;

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
                messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr + 1), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setUser_id(cellstring); // ユーザーID

            // ２つめの項目（ユーザ有効期限）
            columnnum = 2;

            // セルの値を取得
            celldate = excelUtils.getColumnDate(row, columnnum);

            // 必須チェック
            if (celldate == null) {
                messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr + 1), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            user.setUser_due_date(celldate); // ユーザ有効期限

            // パスワードは暗号化する
            user.setPassword(passwordEncoder.encode(Constants.INITIAL_PASSWORD)); // パスワード

            user.setPass_update(Date.valueOf(LocalDate.now())); // パスワード有効期限

            // ３つめの項目
            columnnum = 3;

            // 桁数取得
            columnlength = sqlSharedService.getstrcolumnlength("zm001_user", "employee_id");

            // セルの値を取得
            cellstring = excelUtils.getColumnString(row, columnnum, columnlength);

            // 必須チェック
            if (cellstring == null) {
                messageKey = "e.co.fw.1.014";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr + 1), String.valueOf(columnnum) }));
            }

            // 取得した値をセット
            // TODO 本来はエクセルには社員CDをセットし、そこから社員Mで社員IDを検索してユーザマスタに追加する。
            user.setEmployee_id(cellstring); // 社員ID

            // ４つめの項目（有効フラグ）
            columnnum = 4;

            // 取得した値をセット
            user.setEnabled_flg(excelUtils.getColumnBoolean(row, columnnum)); // 有効フラグ

            AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            user.setInsert_user(user_auth.getUsername()); // 作成者

            try {
                // ユーザー登録処理(user)
                result = userSharedService.insertOne(user);

            } catch (DataIntegrityViolationException e) {

                if ("DuplicateKeyException".equals(e.getClass().getSimpleName())) {
                    // 一意制約エラーが発生した時とき。
                    messageKey = "e.co.fw.2.003";
                    message = "ユーザID " + user.getUser_id() + "が既に登録されているか、社員";
                    throw new ApplicationException(messageKey,
                            massageUtils.getMsg(messageKey, new String[] { message }), e);

                }

                // 参照整合性エラーが発生したとき。
                columnnum = 3;
                message = "社員ID " + user.getEmployee_id() + " が社員マスタに";

                messageKey = "e.co.fw.1.012";
                throw new ApplicationException(messageKey, massageUtils.getMsg(messageKey,
                        new String[] { SheetName, String.valueOf(rowNbr + 1), String.valueOf(columnnum), message }), e);

            }
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

        // TODO チェック内容を記述
//        Cell headerCell = headerRow.getCell(0);
//        String headerStr = headerCell.getStringCellValue();

        return true;
    }
}
