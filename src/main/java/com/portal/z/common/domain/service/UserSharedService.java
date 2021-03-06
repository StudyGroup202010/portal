package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.User;

/**
 *  ユーザマスタ用共通サービス
 *
 */
public interface UserSharedService {

    /**
     * ユーザマスタ追加用メソッド<BR>
     * 
     * ユーザマスタに追加するときは、ユーザロールマスタにも追加する必要があるため、このメソッドで一緒に行う。
     * 
     * @param user ユーザマスタ
     * @return 両方のテーブルに追加できたときtrue。それ以外はfalse。<BR>
     */
    public boolean insertOne(User user);

    /**
     * ユーザマスタ削除用メソッド<BR>
     * 
     * ユーザマスタを削除するときは、ユーザロールマスタも削除する必要があるため、このメソッドで一緒に行う。
     * ※参照整合性制約があるため
     * 
     * @param user_id user_id
     * @return 両方のテーブルの削除が成功したときtrue。それ以外false
     */
    public boolean deleteOne(String user_id);
}
