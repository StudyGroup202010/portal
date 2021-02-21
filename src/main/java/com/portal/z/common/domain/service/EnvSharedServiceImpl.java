package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.repository.EnvMapper;

/**
 * EnvSharedServiceImpl
 *
 */
@Transactional
@Service
public class EnvSharedServiceImpl implements EnvSharedService {

    @Autowired
    EnvMapper envMapper;

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
