package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Userrole;
import com.portal.z.common.domain.repository.UserroleMapper;

/**
 * UserroleService
 *
 */
@Transactional
@Service
public class UserroleService {

    @Autowired
    UserroleMapper userroleMapper;

    /**
     * insert用メソッド.
     * 
     * @param userrole userrole
     * @return insertOne
     */
    public boolean insert(Userrole userrole) {
        return userroleMapper.insertOne(userrole);
    }

    /**
     * １件削除用メソッド.
     * 
     * @param user_id user_id
     * @return deleteOne
     */
    public boolean deleteOne(String user_id) {
        return userroleMapper.deleteOne(user_id);
    }
}
