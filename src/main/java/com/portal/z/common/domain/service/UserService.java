package com.portal.z.common.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;
//JDBC用
import org.springframework.dao.DataAccessException;

import com.portal.z.common.domain.repository.UserDao;

/**
 * UserService
 *
 */
@Transactional
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDao dao;

    /**
     * insert用メソッド.
     * 
     * @param user user
     * @return insertOne
     */
    public boolean insert(User user) {
        return userMapper.insertOne(user);
    }

    /**
     * カウント用メソッド.
     * 
     * @return count
     */
    public int count() {
        return userMapper.count();
    }

    /**
     * 全件取得用メソッド.
     * 
     * @return selectMany
     */
    public List<User> selectMany() {
        // 全件取得
        return userMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     * 
     * @param user_id user
     * @return selectOne
     */
    public User selectOne(String user_id) {
        // selectOne実行
        return userMapper.selectOne(user_id);
    }

    /**
     * １件更新用メソッド.
     * 
     * @param user user
     * @return updateOne
     */
    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    /**
     * １件削除用メソッド.
     * 
     * @param user_id user_id
     * @return deleteOne
     */
    public boolean deleteOne(String user_id) {
        return userMapper.deleteOne(user_id);
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
     * ロックフラグ更新用メソッド.
     * 
     * @param user user
     * @return updateLockflg
     */
    public boolean updateLockflg(User user) {
        return userMapper.updateLockflg(user);
    }

    /**
     * パスワード有効期限更新用メソッド.
     * 
     * @param user user
     * @return updatePassupdate
     */
    public boolean updatePassupdate(User user) {
        return userMapper.updatePassupdate(user);
    }

    /**
     * 条件検索用メソッド.
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return selectBy
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to) {
        // selectBy実行
        return userMapper.selectBy(user_id, user_due_date_from, user_due_date_to);
    }
}
