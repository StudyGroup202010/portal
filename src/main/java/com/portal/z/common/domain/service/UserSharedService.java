package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.model.Userrole;

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
     * @param user     ユーザマスタ
     * @param userrole ユーザロールマスタ
     * @return 両方のテーブルに追加できたときtrue。それ以外はfalse
     */
    public boolean insertOne(User user, Userrole userrole);

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
