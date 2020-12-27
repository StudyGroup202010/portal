package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.model.Userrole;

@Transactional
@Service
public class RegistuserService {

    @Autowired
    private UserroleService userroleService;

    @Autowired
    private UserService userService;

    /**
     * insert用メソッド.
     */
    public boolean insertOne(User user, Userrole userrole) {

        // 登録実行
        boolean result_1 = userService.insert(user);
        boolean result_2 = userroleService.insert(userrole);

        // ユーザー登録結果の判定
        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * delete用メソッド.
     */
    public boolean deleteOne(String user_id) {

        // 削除実行
        boolean result_1 = userroleService.deleteOne(user_id);
        boolean result_2 = userService.deleteOne(user_id);

        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }
}
