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

    public List<User> selectMany() {
        return userMapper.selectMany();
    }

    public User selectOne(String user_id) {
        return userMapper.selectOne(user_id);
    }

    public boolean updateOne(User user) {
        return userMapper.updateOne(user);
    }

    public void userCsvOut() throws DataAccessException {
        dao.userCsvOut();
    }

    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to) {
        return userMapper.selectBy(user_id, user_due_date_from, user_due_date_to);
    }
}
