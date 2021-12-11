package com.portal.a.organization.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Company;
import com.portal.a.common.domain.model.Organization;

/**
 * OrganizationService
 *
 */
public interface OrganizationService {

    /**
     * 全件取得用メソッド.
     * 
     * @return OrganizationList
     */
    public List<Organization> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param organization_cd organization_cd
     * @return organization
     */
    public Organization selectOne(String organization_cd);

    /**
     * 登録用メソッド
     * 
     * @param organization organization
     * @return true/false
     */
    public boolean insertOne(Organization organization);

    /**
     * １件更新用メソッド.
     * 
     * @param organization organization
     * @return true/false
     */
    public boolean updateOne(Organization organization);

    /**
     * １件削除用メソッド.
     * 
     * @param organization_cd organization_cd
     * @return true/false
     */
    public boolean deleteOne(String organization_cd);

    /**
     * 条件検索用メソッド.
     * 
     * @param organization_name organization_name
     * @param company_cd        company_cd
     * @param start_yearmonth   start_yearmonth
     * @param end_yearmonth     end_yearmonth
     * @param biko              biko
     * @return OrganizationList
     */
    public List<Organization> selectBy(String organization_name, String company_cd, String start_yearmonth, String end_yearmonth, String biko);
    
    /**
     * 全件取得用メソッド（会社マスタ）.
     * 
     * @return Company
     */
    public List<Company> selectManyCompany();
}
