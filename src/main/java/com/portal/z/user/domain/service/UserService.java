package com.portal.z.user.domain.service;

import java.util.List;
import com.portal.z.common.domain.model.User;

/**
 * UserService
 *
 */
public interface UserService {

    /**
     * 全件取得用メソッド.
     * 
     * @return User
     */
    public List<User> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param user_id user
     * @return User
     */
    public User selectOne(String user_id);

    /**
     * １件更新用メソッド.
     * 
     * @param user user
     * @return true/false
     */
    public boolean updateOne(User user);

    /**
     * 条件検索用メソッド.
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return User
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to);

}
