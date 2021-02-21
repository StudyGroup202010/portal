package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.User;

/**
 * UserSharedService
 *
 */
public interface UserSharedService {

    /**
     * insert用メソッド.<BR>
     * 
     * ユーザマスタとユーザロールマスタに追加する<BR>
     * 
     * ユーザマスタに追加するときは、ユーザロールマスタも追加しないといけないので、１つのメソッドにまとめました。
     * 
     * @param user ユーザマスタ
     * @return 両方のテーブルに追加できたときtrue。それ以外はfalse。<BR>
     *         アプリケーションエラー<BR>
     *         ・環境マスタに"ROLE_NAME_G"が登録されていないとき。<BR>
     *         ・一意制約エラーが発生したとき。
     */
    public boolean insertOne(User user);

    /**
     * delete用メソッド.<BR>
     * 
     * ユーザマスタとユーザロールマスタを削除する<BR>
     * 
     * ユーザマスタを削除するときは、まずユーザロールマスタを削除しないといけないので、１つのメソッドにまとめました。<BR>
     * ※参照整合性制約があるため
     * 
     * @param user_id user_id
     * @return 削除が成功したときtrue。それ以外false
     */
    public boolean deleteOne(String user_id);
}
