package com.portal.a.env.domain.service;

import java.util.List;
import com.portal.a.common.domain.model.Env;

/**
 * EnvService
 *
 */
public interface EnvService {

    /**
     * 全件取得用メソッド.
     * 
     * @return EnvList
     */
    public List<Env> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param env_id env_id
     * @return env
     */
    public Env selectOne(String env_id);

    /**
     * 登録用メソッド
     * 
     * @param env env
     * @return true/false
     */
    public boolean insertOne(Env env);

    /**
     * １件更新用メソッド.
     * 
     * @param env env
     * @return true/false
     */
    public boolean updateOne(Env env);

    /**
     * １件削除用メソッド.
     * 
     * @param env_id env_id
     * @return true/false
     */
    public boolean deleteOne(String env_id);

    /**
     * 条件検索用メソッド.
     * 
     * @param env_id  env_id
     * @param env_kbn env_kbn
     * @param env_txt env_txt
     * @param biko    biko
     * @return EnvList
     */
    public List<Env> selectBy(String env_id, String env_kbn, String env_txt, String biko);
}
