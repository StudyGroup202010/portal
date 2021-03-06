package com.portal.z.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import com.portal.z.common.domain.model.Userrole;

/**
 * UserroleMapper
 *
 */
@Mapper
public interface UserroleMapper {
    /**
     * 登録用メソッド
     * 
     * @param userrole userrole
     * @return true/false
     */
    public boolean insertOne(Userrole userrole);

    /**
     * １件削除用メソッド
     * 
     * @param user_id user_id
     * @return true/false
     */
    public boolean deleteOne(String user_id);
}
