package com.portal.z.common.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Role;
import com.portal.z.common.domain.repository.RoleMapper;

/**
 * RoleService
 *
 */
@Transactional
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * insert用メソッド.
     * 
     * @param role role
     * @return insertOne
     */
    public boolean insert(Role role) {
        return roleMapper.insertOne(role);
    }

    /**
     * 全件取得用メソッド.
     * 
     * @return selectMany
     */
    public List<Role> selectMany() {
        // 全件取得
        return roleMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     * 
     * @param role_id role_id
     * @return selectOne
     */
    public Role selectOne(String role_id) {
        // selectOne実行
        return roleMapper.selectOne(role_id);
    }

    /**
     * １件更新用メソッド.
     * 
     * @param role role
     * @return updateOne
     */
    public boolean updateOne(Role role) {
        return roleMapper.updateOne(role);
    }

    /**
     * １件削除用メソッド.
     * 
     * @param role_id role_id
     * @return deleteOne
     */
    public boolean deleteOne(String role_id) {
        return roleMapper.deleteOne(role_id);
    }

    /**
     * ロールＩＤ取得用メソッド.<BR>
     * 環境マスタの環境ＩＤからロールＩＤを取得する ※ROLE_NAME_AとROLE_NAME_Gを想定
     * 
     * @param env_id env_id
     * @return selectRoleid
     */
    public Role selectRoleid(String env_id) {
        return roleMapper.selectRoleid(env_id);
    }
}
