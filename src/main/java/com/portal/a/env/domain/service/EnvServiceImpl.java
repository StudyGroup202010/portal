package com.portal.a.env.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.a.common.domain.model.Env;

import com.portal.a.common.domain.repository.EnvMapper;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.exception.ApplicationException;

/**
 * EnvServiceImpl
 *
 */
@Transactional
@Service
public class EnvServiceImpl implements EnvService {

    @Autowired
    EnvMapper envMapper;

    @Autowired
    private MassageUtils massageUtils;

    public List<Env> selectMany() {
        return envMapper.selectMany();
    }

    public Env selectOne(String env_id) {
        return envMapper.selectOne(env_id);
    }

    public boolean insertOne(Env env) {
        try {
            return envMapper.insertOne(env);

        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生した時はビジネス例外として返す。
            String messageKey = "e.co.fw.2.003";
            throw new ApplicationException(messageKey,
                    massageUtils.getMsg(messageKey, new String[] { env.getEnv_id() }), e);
        }
    }

    public boolean updateOne(Env env) {
        return envMapper.updateOne(env);
    }

    public boolean deleteOne(String env_id) {
        return envMapper.deleteOne(env_id);
    }

    public List<Env> selectBy(String env_id, String env_kbn, String env_txt, String biko) {
        return envMapper.selectBy(env_id, env_kbn, env_txt, biko);
    }
}
