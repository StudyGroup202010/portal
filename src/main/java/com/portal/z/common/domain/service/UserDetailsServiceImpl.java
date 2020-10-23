package com.portal.z.common.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//
//SecurityConfigから呼ばれる
//
@Component("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        //ユーザ情報を取得
    	UserDetails user = userService.selectUserDetails(username);
		if (user == null ) {
	    	//ToDoここでuser_idが拾えなかったときの処理
			//
			//
			
			System.out.println("userが拾えなかった1 ：" + user);
			return null;
		}
		
		System.out.println("user1：" + user);
    	    	
        return user;
    }
}
