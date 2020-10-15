package com.portal.z.common.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.repository.EnvMapper;
import com.portal.z.common.exception.EnvEmptyResultDataAccessException;


@Transactional
@Service
public class EnvService {

    @Autowired
    EnvMapper envMapper;

    /**
     * insert用メソッド.
     */
    public boolean insert(Env env) {
    	return envMapper.insertOne(env);
    }

    /**
     * 全件取得用メソッド.
     */
    public List<Env> selectMany() {
        // 全件取得
        return envMapper.selectMany();
    }

    /**
     * １件取得用メソッド.
     */
    public Env selectOne(String env_id) {
        // selectOne実行     
		Env env = envMapper.selectOne(env_id);
		//
		// 環境マスタに値が無いと正しく動作しないので、結果が無い場合は例外を発生させることにします。
		//　独自例外（Env_EmptyResultDataAccessException）を使っています。
		//
		if (env == null) {
			throw new EnvEmptyResultDataAccessException("環境マスタに" + env_id + "の値が登録されていません", 0);
		}
		return env;
    }

    /**
     * １件更新用メソッド.
     */
    public boolean updateOne(Env env) {
    	return envMapper.updateOne(env);
    }

    /**
     * １件削除用メソッド.
     */
    public boolean deleteOne(String env_id) {
    	return envMapper.deleteOne(env_id);
    }
 
}
