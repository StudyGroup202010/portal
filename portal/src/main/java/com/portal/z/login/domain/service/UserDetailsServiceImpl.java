package com.portal.z.login.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginService loginService;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //リポジトリー(DAO)からUserDetailsを取得
    	UserDetails user = loginService.selectOne(username);
    	
        return user;
    }

}
