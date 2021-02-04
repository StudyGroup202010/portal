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
     * @return insertOne
     */
    public boolean insertOne(User user);

    /**
     * １件検索用メソッド
     * 
     * @param user_id user_id
     * @return selectOne
     */
    public User selectOne(String user_id);

    /**
     * 全件検索用メソッド
     * 
     * @return selectMany
     */
    public List<User> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param user user
     * @return updateOne
     */
    public boolean updateOne(User user);

    /**
     * １件削除用メソッド
     * 
     * @param user_id user_id
     * @return deleteOne
     */
    public boolean deleteOne(String user_id);

    /**
     * ロックフラグ更新用メソッド
     * 
     * @param user user
     * @return updateLockflg
     */
    public boolean updateLockflg(User user);

    /**
     * パスワード有効期限更新用メソッド
     * 
     * @param user user
     * @return updatePassupdate
     */
    public boolean updatePassupdate(User user);

    /**
     * 条件検索用メソッド
     * 
     * @param user_id            user_id
     * @param user_due_date_from user_due_date_from
     * @param user_due_date_to   user_due_date_to
     * @return selectBy
     */
    public List<User> selectBy(String user_id, String user_due_date_from, String user_due_date_to);
}
