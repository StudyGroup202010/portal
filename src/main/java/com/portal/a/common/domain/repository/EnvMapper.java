package com.portal.a.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.a.common.domain.model.Env;

/**
 * EnvMapper
 *
 */
@Mapper
public interface EnvMapper {

    /**
     * 登録用メソッド
     * 
     * @param env env
     * @return true/false
     */
    public boolean insertOne(Env env);

    /**
     * １件検索用メソッド
     * 
     * @param env_id env_id
     * @return Env
     */
    public Env selectOne(String env_id);

    /**
     * 全件検索用メソッド
     * 
     * @return EnvList
     */
    public List<Env> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param env env
     * @return true/false
     */
    public boolean updateOne(Env env);

    /**
     * １件削除用メソッド
     * 
     * @param env_id env_id
     * @return true/false
     */
    public boolean deleteOne(String env_id);

    /**
     * 条件検索用メソッド
     * 
     * @param env_id  env_id
     * @param env_kbn env_kbn
     * @param env_txt env_txt
     * @param biko    biko
     * @return EnvList
     */
    public List<Env> selectBy(String env_id, String env_kbn, String env_txt, String biko);
}
