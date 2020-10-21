package com.portal.z.common.domain.repository;

import org.springframework.dao.DataAccessException;

public interface UserDao {
    public void userCsvOut() throws DataAccessException;
}
