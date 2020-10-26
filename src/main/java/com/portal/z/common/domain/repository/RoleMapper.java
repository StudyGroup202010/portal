package com.portal.z.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.Role;

@Mapper
public interface RoleMapper {

    // 登録用メソッド
    public boolean insertOne(Role role);

    // １件検索用メソッド
    public Role selectOne(String role_id);

    // 全件検索用メソッド
    public List<Role> selectMany();

    // １件更新用メソッド
    public boolean updateOne(Role role);

    // １件削除用メソッド
    public boolean deleteOne(String role_id);
    
    //　ロールＩＤ検索用メソッド
    public Role selectRoleid(String env_id);
    
}
