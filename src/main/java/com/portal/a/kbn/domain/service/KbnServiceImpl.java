package com.portal.a.kbn.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Kbn;
import com.portal.a.common.domain.repository.KbnMapper;

/**
 * KbnServiceImpl のインターフェース
 *
 */
@Transactional
@Service
public class KbnServiceImpl implements KbnService {

    @Autowired
    KbnMapper kbnMapper;

    public List<Kbn> selectMany() {
        return kbnMapper.selectMany();
    }

    public Kbn selectOne(Kbn kbn) {
        return kbnMapper.selectOne(kbn);
    }

    public boolean insertOne(Kbn kbn) {
        return kbnMapper.insertOne(kbn);
    }

    public boolean updateOne(Kbn kbn) {
        return kbnMapper.updateOne(kbn);
    }

    public boolean deleteOne(Kbn kbn) {
        return kbnMapper.deleteOne(kbn);
    }

    public List<Kbn> selectBy(Kbn kbn) {
        return kbnMapper.selectBy(kbn);
    }
}
