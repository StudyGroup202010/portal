package com.portal.z.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.User;

/**
 * UserMapper
 *
 */
@Mapper
public interface UserMapper {

    /**
     * カウント用メソッド
     * 
     * @return count
     */
    public int count();

    /**
     * 登録用メソッド
     * 
     * @param user user
     * @return true/false
     */
    public boolean insertOne(User user);

    /**
     * １件検索用メソッド
     * 
     * @param user_id     user_id
     * @param employee_id employee_id
     * @return User
     */
    public User selectOne(String user_id, String employee_id);

    /**
     * 全件検索用メソッド
     * 
     * @return UserList
     */
    public List<User> selectMany();

    /**
     * 全件検索用メソッド
     * 
     * @return UserList
     */
    public List<User> selectManyExceptRetire();

    /**
     * １件更新用メソッド
     * 
     * @param user user
     * @return true/false
     */
    public boolean updateOne(User user);

    /**
     * １件削除用メソッド
     * 
     * @param user_id user_id
     * @return true/false
     */
    public boolean deleteOne(String user_id);

    /**
     * ロックフラグ更新用メソッド
     * 
     * @param user user
     * @return true/false
     */
    public boolean updateLockflg(User user);

    /**
     * パスワード及びパスワード有効期限更新用メソッド
     * 
     * @param user user
     * @return true/false
     */
    public boolean updatePassupdate(User user);

    /**
     * 条件検索用メソッド
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return UserList
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to);

    /**
     * 失敗回数をリセット(0回に更新)するメソッド
     * 
     * @param user_id user_id
     * @return 成功ならtrue/失敗ならfalse
     */
    public boolean updateLoginMissTimes(String user_id);

    /**
     * 社員ID条件検索用メソッド
     * 
     * @param employee_id employee_id
     * @return User
     */
    public User selectByEmployeeid(String employee_id);
}
