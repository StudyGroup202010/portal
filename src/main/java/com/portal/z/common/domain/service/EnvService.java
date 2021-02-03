package com.portal.z.common.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.repository.EnvMapper;

/**
 * EnvService
 *
 */
@Transactional
@Service
public class EnvService {

    @Autowired
    EnvMapper envMapper;

    /**
     * insert用メソッド.
     * 
     * @param env env
     * @return insertOne
     */
    public boolean insert(Env env) {
        return envMapper.insertOne(env);
    }

    /**
     * 全件取得用メソッド.
     * 
     * @return selectMany
     */
    public List<Env> selectMany() {
        // 全件取得
        return envMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     * 
     * @param env_id env_id
     * @return selectOne
     */
    public Env selectOne(String env_id) {
        // selectOne実行
        return envMapper.selectOne(env_id);
    }

    /**
     * １件更新用メソッド.
     * 
     * @param env env
     * @return updateOne
     */
    public boolean updateOne(Env env) {
        return envMapper.updateOne(env);
    }

    /**
     * １件削除用メソッド.
     * 
     * @param env_id env_id
     * @return deleteOne
     */
    public boolean deleteOne(String env_id) {
        return envMapper.deleteOne(env_id);
    }

    /**
     * 環境マスタから数値項目を１件取得する。<BR>
     * 
     * @param env_id env_id
     * @return 数値の場合は値を返す。数値で無い場合はnull
     */
    public Env selectIntOne(String env_id) {
        try {
            Env env = envMapper.selectOne(env_id);

            if (env != null) {
                // 取得した値が数値かどうかを確認する
                Integer.parseInt(env.getEnv_txt());
            }

            return env;

        } catch (NumberFormatException e) {
            // 数値以外の値が登録されていたらnullを返す。
            return null;
        }
    }

}
