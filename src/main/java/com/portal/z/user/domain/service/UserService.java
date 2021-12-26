package com.portal.z.user.domain.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MultipartFile;

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.exception.ApplicationException;

/**
 * UserService
 *
 */
public interface UserService {

    /**
     * 全件取得用メソッド.
     * 
     * @return User
     */
    public List<User> selectMany();

    /**
     * 全件取得用メソッド（退職者を除く）
     * 
     * @return Employee
     */
    public List<Employee> selectManyExceptRetireeEmployee();

    /**
     * １件取得（ユーザID）用メソッド.
     * 
     * @param user_id user_id
     * @return User
     */
    public User selectOne(String user_id);

    /**
     * １件取得（社員ID）用メソッド.
     * 
     * @param employee_id employee_id
     * @return User
     */
    public User selectOneByEmployeeid(String employee_id);

    /**
     * １件更新用メソッド.
     * 
     * @param user user
     * @return true/false
     * @throws DataIntegrityViolationException ユーザマスタで参照整合性制約エラーが発生した時
     */
    public boolean updateOne(User user);

    /**
     * 条件検索用メソッド.
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return User
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to);

    /**
     * Excelデータ追加用メソッド.
     * 
     * @param file      取り込むエクセル
     * @param SheetName 取り込むシートの名称
     * @return エクセルファイルの内容をユーザマスタに登録できたらtrue
     * @throws IOException                     エクセルファイルの読み込みでエラーが発生したとき
     * @throws EncryptedDocumentException      エクセル関係の操作でエラーが発生したとき
     * @throws DuplicateKeyException           ユーザマスタで一意制約エラーが発生した時
     * @throws DataIntegrityViolationException ユーザマスタで参照整合性制約エラーが発生した時
     * @throws ApplicationException            <BR>
     *                                         ・指定したシートが無かったとき<BR>
     *                                         ・指定したシートにヘッダー行が間違えているとき<BR>
     *                                         ・シートにデータ行が登録されていなかったとき<BR>
     *                                         ・必須項目に値が登録されていなかったとき
     */
    public boolean insertFromExcel(MultipartFile file, String SheetName) throws EncryptedDocumentException, IOException,
            DuplicateKeyException, DataIntegrityViolationException, ApplicationException;
}
