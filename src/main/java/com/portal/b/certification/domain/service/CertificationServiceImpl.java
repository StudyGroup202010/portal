package com.portal.b.certification.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.b.common.domain.model.Certification;
import com.portal.b.common.domain.repository.CertificationMapper;

/**
 * CertificationServiceImpl
 *
 */
@Transactional
@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    CertificationMapper certificationMapper;

    // 資格マスタ
    public List<Certification> selectMany() {
        return certificationMapper.selectMany();
    }
}
