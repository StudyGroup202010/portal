package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Role;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.model.Userrole;
import com.portal.z.common.domain.repository.RoleMapper;
import com.portal.z.common.domain.repository.UserMapper;
import com.portal.z.common.domain.repository.UserroleMapper;
import com.portal.z.common.exception.ApplicationException;
import com.portal.z.common.exception.HttpErrorsImpl;
import com.portal.z.common.domain.util.Constants;

/**
 * ユーザマスタ用共通サービス
 *
 */
@Transactional
@Service
public class UserSharedServiceImpl implements UserSharedService{

    @Autowired
    private UserroleMapper userroleMapper;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    public boolean insertOne(User user) {

        // 環境マスタに登録したロール名（一般ユーザ）のrole_idを取得する
        // 取得できない(取得結果がnull)の場合、処理を中止する
        Role role = roleMapper.selectRoleid(Constants.ROLE_NAME.ROLE_NAME_G.name());
        if (role == null) {
            throw new ApplicationException(HttpErrorsImpl.NOTFOUND_ENV, Constants.ROLE_NAME.ROLE_NAME_G.name());
        }

        // ユーザロールマスタinsert用変数
        Userrole userrole = new Userrole();
        userrole.setUser_id(user.getUser_id()); // ユーザーID
        userrole.setRole_id(role.getRole_id()); // ロールID

        // 登録実行
        try {
            boolean result_1 = userMapper.insertOne(user);
            boolean result_2 = userroleMapper.insertOne(userrole);

            // ユーザー登録結果の判定
            if (result_1 == true && result_2 == true) {
                return true;
            } else {
                return false;
            }

        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生した時はビジネス例外として返す。
            throw new ApplicationException(HttpErrorsImpl.DUPLICATED, e, user.getUser_id());
        }
    }

    public boolean deleteOne(String user_id) {

        // 削除実行
        boolean result_1 = userroleMapper.deleteOne(user_id);
        boolean result_2 = userMapper.deleteOne(user_id);

        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }
}
