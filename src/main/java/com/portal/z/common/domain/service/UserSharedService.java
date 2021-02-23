package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.User;

/**
 * UserSharedService
 *
 */
public interface UserSharedService {

    /**
     * insertOne<BR>
     * 
     * @param user ユーザマスタ
     * @return 両方のテーブルに追加できたときtrue。それ以外はfalse。<BR>
     */
    public boolean insertOne(User user);

    /**
     * deleteOne<BR>
     * 
     * @param user_id user_id
     * @return 削除が成功したときtrue。それ以外false
     */
    public boolean deleteOne(String user_id);
}
