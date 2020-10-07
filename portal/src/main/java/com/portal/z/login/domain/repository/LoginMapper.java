package com.portal.z.login.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.portal.z.common.domain.model.Role;

@Mapper
public interface LoginMapper {

    // 全件検索用メソッド
    public List<Role> selectMany(String user_id);

}
