package com.portal.a.env.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.a.common.domain.model.Env;

import com.portal.a.common.domain.repository.EnvMapper;

/**
 * EnvServiceImpl
 *
 */
@Transactional
@Service
public class EnvServiceImpl implements EnvService {

    @Autowired
    EnvMapper envMapper;

    public List<Env> selectMany() {
        return envMapper.selectMany();
    }

    public Env selectOne(String env_id) {
        return envMapper.selectOne(env_id);
    }

    public boolean insertOne(Env env) {
        return envMapper.insertOne(env);
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
