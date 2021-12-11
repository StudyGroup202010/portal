package com.portal.a.organization.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Company;
import com.portal.a.common.domain.model.Organization;
import com.portal.a.common.domain.repository.CompanyMapper;
import com.portal.a.common.domain.repository.OrganizationMapper;

/**
 * OrganizationServiceImpl
 *
 */
@Transactional
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;
    
    @Autowired
    CompanyMapper companyMapper;

    public List<Organization> selectMany() {
        return organizationMapper.selectMany();
    }

    public Organization selectOne(String organization_cd) {
        return organizationMapper.selectOne(organization_cd);
    }

    public boolean insertOne(Organization organization) {
        return organizationMapper.insertOne(organization);
    }

    public boolean updateOne(Organization organization) {
        return organizationMapper.updateOne(organization);
    }

    public boolean deleteOne(String organization_cd) {
        return organizationMapper.deleteOne(organization_cd);
    }

    public List<Organization> selectBy(String organization_name, String company_cd, String start_yearmonth, String end_yearmonth, String biko) {
        return organizationMapper.selectBy(organization_name, company_cd, start_yearmonth, end_yearmonth, biko);
    }
    public List<Company> selectManyCompany() {
        return companyMapper.selectMany();
    }

}
