package com.portal.z.common.domain.repository;

import org.springframework.dao.DataAccessException;

/**
 * UserDao
 *
 */
public interface UserDao {
    /**
     * userCsvOut()<BR>
     * SQL取得結果をサーバーにCSVで保存する
     * 
     * @throws DataAccessException エラー
     */
    public void userCsvOut() throws DataAccessException;
}
