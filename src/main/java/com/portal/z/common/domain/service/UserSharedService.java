package com.portal.z.common.domain.service;

import java.text.ParseException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.exception.ApplicationException;

/**
 * ユーザマスタ用共通サービス
 *
 */
public interface UserSharedService {

    /**
     * ユーザマスタ追加用メソッド<BR>
     * ユーザマスタに追加するときは、ユーザロールマスタにも追加する必要があるため、このメソッドで一緒に行う。<BR>
     * 
     * 以下の場合はアプリケーションエラー（ApplicationException）となる<BR>
     * ・環境マスタに"ROLE_NAME_G"が登録されていないとき。<BR>
     * 
     * ・一意制約エラーが発生したとき。
     * 
     * @param user ユーザマスタ
     * @return 両方のテーブルに追加できたときtrue。それ以外はfalse。
     * @throws ApplicationException            環境マスタに"ROLE_NAME_G"が登録されていないとき<BR>
     * @throws DuplicateKeyException           ユーザマスタで一意制約エラーが発生した時<BR>
     * @throws DataIntegrityViolationException ユーザマスタで参照整合性制約エラーが発生した時
     */
    public boolean insertOne(User user)
            throws DuplicateKeyException, DataIntegrityViolationException, ApplicationException;

    /**
     * ユーザマスタ削除用メソッド<BR>
     * 
     * ユーザマスタを削除するときは、ユーザロールマスタも削除する必要があるため、このメソッドで一緒に行う。<BR>
     * ※参照整合性制約があるため
     * 
     * @param user_id user_id
     * @return 両方のテーブルの削除が成功したときtrue。それ以外false
     */
    public boolean deleteOne(String user_id);

    /**
     * パスワードを更新する.
     * 
     * @param userId   userId
     * @param password password
     * @return true/false
     * @throws ParseException ParseException
     */
    public boolean updatePasswordDate(String userId, String password) throws ParseException;
}
