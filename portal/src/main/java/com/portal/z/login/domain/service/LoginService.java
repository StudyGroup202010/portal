package com.portal.z.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;

import com.portal.z.common.domain.model.Role;
import com.portal.z.login.domain.repository.LoginMapper;
import com.portal.z.login.domain.repository.LoginDao;


@Transactional
@Service
public class LoginService {

    @Autowired
    LoginDao dao;
    
    @Autowired
    LoginMapper loginMapper;
    
    /**
     * １件取得用メソッド.
     */
    public UserDetails selectOne(String user_id) throws DataAccessException {
        // selectOne実行
        return dao.selectOne(user_id);
    }
    
    /**
     * ユーザに紐つく権限取得用メソッド.
     */
    public List<Role> selectMany(String user_id) {
        // 権限取得
        return loginMapper.selectMany(user_id);
    }
}
