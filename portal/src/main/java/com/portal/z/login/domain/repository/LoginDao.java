package com.portal.z.login.domain.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginDao {
  
    public UserDetails selectOne(String user_id) throws DataAccessException;

}
