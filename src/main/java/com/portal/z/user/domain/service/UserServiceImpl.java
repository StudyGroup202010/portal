package com.portal.z.user.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;
import org.springframework.dao.DataAccessException;
import com.portal.z.common.domain.repository.UserDao;

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
    UserDao dao;

    /**
     * 全件取得用メソッド.
     * 
     * @return List<User>
     */
    public List<User> selectMany() {
        // 全件取得
        return userMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     * 
     * @param user_id user
     * @return User
     */
    public User selectOne(String user_id) {
        // selectOne実行
        return userMapper.selectOne(user_id);
    }

    /**
     * １件更新用メソッド.
     * 
     * @param user user
     * @return boolean
     */
    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    /**
     * CSV出力用メソッド.
     * 
     * @throws DataAccessException DataAccessException
     */
    public void userCsvOut() throws DataAccessException {
        dao.userCsvOut();
    }

    /**
     * 条件検索用メソッド.
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return List<User>
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to) {
        // selectBy実行
        return userMapper.selectBy(user_id, user_due_date_from, user_due_date_to);
    }
}
