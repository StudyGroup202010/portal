package com.portal.z.common.domain.repository;

import org.springframework.dao.DataAccessException;

/**
 * UserDao
 *
 */
public interface UserDao {
    /**
     * userCsvOut()
     * 
     * @throws DataAccessException エラー
     */
    public void userCsvOut() throws DataAccessException;
}
