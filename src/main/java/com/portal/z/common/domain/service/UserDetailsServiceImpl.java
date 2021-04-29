package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.repository.LoginUserRepository;

/**
 * UserDetailsService<BR>
 * 
 * SecurityConfigから呼ばれる
 *
 */
@Transactional
@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginUserRepository repository;

    /**
     * ユーザ情報を取得
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ユーザ情報を取得
        return repository.selectOne(username);
    }
}
