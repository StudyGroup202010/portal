package com.portal.b.industry.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.b.common.domain.model.Industry;
import com.portal.b.common.domain.repository.IndustryMapper;

/**
 * IndustryServiceImpl
 *
 */
@Transactional
@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    IndustryMapper industryMapper;

    // 産業分類マスタ
    public List<Industry> selectMany() {
        return industryMapper.selectMany();
    }

    public Industry selectOne(String industry_id) {
        return industryMapper.selectOne(industry_id);
    }

    public boolean updateOne(Industry industry) {
        return industryMapper.updateOne(industry);
    }

    public boolean deleteOne(String industry_id) {
        return industryMapper.deleteOne(industry_id);
    }
}
