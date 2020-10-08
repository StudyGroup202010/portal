package com.portal.z.common.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Role;
import com.portal.z.common.domain.repository.RoleMapper;


@Transactional
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * insert用メソッド.
     */
    public boolean insert(Role role) {
    	return roleMapper.insertOne(role);
    }

    /**
     * 全件取得用メソッド.
     */
    public List<Role> selectMany() {
        // 全件取得
        return roleMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     */
    public Role selectOne(String role_id) {
        // selectOne実行
        return roleMapper.selectOne(role_id);
    }

    /**
     * １件更新用メソッド.
     */
    public boolean updateOne(Role role) {
    	return roleMapper.updateOne(role);
    }

    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(String role_id) {
    	return roleMapper.deleteOne(role_id);
    }
    
    /**
     * ロールＩＤ取得用メソッド.
     * 環境マスタの環境ＩＤからロールＩＤを取得する
     * ※ROLE_NAME_AとROLE_NAME_Gを想定
     */
    public Role selectRoleid(String env_id) {
        // selectOne実行
        return roleMapper.selectRoleid(env_id);
    }
}
