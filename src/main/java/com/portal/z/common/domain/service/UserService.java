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

@Transactional
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDao dao;

    /**
     * insert用メソッド.
     */
    public boolean insert(User user) {
        return userMapper.insertOne(user);
    }

    /**
     * カウント用メソッド.
     */
    public int count() {
        return userMapper.count();
    }

    /**
     * 全件取得用メソッド.
     */
    public List<User> selectMany() {
        // 全件取得
        return userMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     */
    public User selectOne(String user_id) {
        // selectOne実行
        return userMapper.selectOne(user_id);
    }
    
    /**
     * ｃｓｖ取得用メソッド.
     */
    public List<User> selectCsv() {
        // csv用に取得
        return userMapper.selectCsv();
    }

    /**
     * １件更新用メソッド.
     */
    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(String user_id) {
        return userMapper.deleteOne(user_id);
    }

    /**
     * CSV出力用メソッド.
     */
    public void userCsvOut() throws DataAccessException{
        dao.userCsvOut();
    }

    /**
     * ロックフラグ更新用メソッド.
     */
    public boolean updateLockflg(User user) {
        return userMapper.updateLockflg(user);
    }

    /**
     * パスワード有効期限更新用メソッド.
     */
    public boolean updatePassupdate(User user) {
        return userMapper.updatePassupdate(user);
    }

}
