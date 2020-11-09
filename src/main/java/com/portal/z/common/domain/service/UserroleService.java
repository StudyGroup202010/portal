package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Userrole;
import com.portal.z.common.domain.repository.UserroleMapper;

@Transactional
@Service
public class UserroleService {

    @Autowired
    UserroleMapper userroleMapper;

    /**
     * insert用メソッド.
     */
    public boolean insert(Userrole userrole) {
        return userroleMapper.insertOne(userrole);
    }

    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(String user_id) {
        return userroleMapper.deleteOne(user_id);
    }
}
