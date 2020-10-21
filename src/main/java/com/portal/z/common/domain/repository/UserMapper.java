package com.portal.z.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.User;

@Mapper
public interface UserMapper {

    // カウント用メソッド
    public int count();
	
    // 登録用メソッド
    public boolean insertOne(User user);

    // １件検索用メソッド
    public User selectOne(String user_id);

    // 全件検索用メソッド
    public List<User> selectMany();

    // １件更新用メソッド
    public boolean updateOne(User user);

    // １件削除用メソッド
    public boolean deleteOne(String user_id);
    
    // ロックフラグ更新用メソッド
    public boolean updateLockflg(User user);
    
    // パスワード有効期限更新用メソッド
    public boolean updatePassupdate(User user);
}
