package com.portal.a.company.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Company;
import com.portal.a.common.domain.repository.CompanyMapper;

/**
 * CompanyServiceImpl
 *
 */
@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    public List<Company> selectMany() {
        return companyMapper.selectMany();
    }

    public Company selectOne(String company_cd) {
        return companyMapper.selectOne(company_cd);
    }

    public boolean insertOne(Company company) {
        return companyMapper.insertOne(company);
    }

    public boolean updateOne(Company company) {
        return companyMapper.updateOne(company);
    }

    public boolean deleteOne(String company_cd) {
        return companyMapper.deleteOne(company_cd);
    }

    public List<Company> selectBy(String company_name, String biko) {
        return companyMapper.selectBy(company_name, biko);
    }
}
