package com.portal.z.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.Role;

/**
 * RoleMapper
 *
 */
@Mapper
public interface RoleMapper {

    /**
     * 登録用メソッド
     * 
     * @param role role
     * @return true/false
     */
    public boolean insertOne(Role role);

    /**
     * １件検索用メソッド
     * 
     * @param role_id role_id
     * @return Role
     */
    public Role selectOne(String role_id);

    /**
     * 全件検索用メソッド
     * 
     * @return RoleList
     */
    public List<Role> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param role role
     * @return true/false
     */
    public boolean updateOne(Role role);

    /**
     * １件削除用メソッド
     * 
     * @param role_id role_id
     * @return true/false
     */
    public boolean deleteOne(String role_id);

    /**
     * ロールＩＤ検索用メソッド
     * 
     * @param env_id env_id
     * @return Role
     */
    public Role selectRoleid(String env_id);

}
