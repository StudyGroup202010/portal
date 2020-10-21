package com.portal.z.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import com.portal.z.common.domain.model.Userrole;

@Mapper
public interface UserroleMapper {
    // 登録用メソッド
    public boolean insertOne(Userrole userrole);

    // １件削除用メソッド
    public boolean deleteOne(String user_id);
}
