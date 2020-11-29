package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class RegistuserService {

    @Autowired
    private UserroleService userroleService;

    @Autowired
    private UserService userService;

    /**
     * delete用メソッド.
     */
    public boolean deleteOne(String user_id) {

        //削除実行
        boolean result_1 = userroleService.deleteOne(user_id);
        boolean result_2 = userService.deleteOne(user_id);

        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        } 
    }
}
