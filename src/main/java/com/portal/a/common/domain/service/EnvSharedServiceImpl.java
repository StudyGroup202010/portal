package com.portal.a.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Env;
import com.portal.a.common.domain.repository.EnvMapper;


/**
 * 環境マスタ用共通Service
 *
 */
@Transactional
@Service
public class EnvSharedServiceImpl implements EnvSharedService {

    @Autowired
    EnvMapper envMapper;

    public Integer selectIntOne(String env_id) {

        Env env = envMapper.selectOne(env_id);

        if (env == null)
            return null;

        try {
            // 取得した値が数値かどうかを確認する
            return Integer.parseInt(env.getEnv_txt());

        } catch (NumberFormatException e) {
            // 数値以外の値が登録されていた。
            return null;
        }
    }
}
