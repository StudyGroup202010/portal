package com.portal.z.common.domain.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsDao {
  
    public UserDetails selectUserDetails(String user_id) throws DataAccessException;

}
