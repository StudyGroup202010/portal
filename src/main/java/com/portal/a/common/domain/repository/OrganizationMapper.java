package com.portal.a.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.a.common.domain.model.Organization;

/**
 * OrganizationMapper
 *
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 登録用メソッド
     * 
     * @param organization organization
     * @return true/false
     */
    public boolean insertOne(Organization organization);

    /**
     * １件検索用メソッド
     * 
     * @param organization_cd organization_cd
     * @return Organization
     */
    public Organization selectOne(String organization_cd);

    /**
     * 全件検索用メソッド
     * 
     * @return OrganizationList
     */
    public List<Organization> selectManyorganization();

    /**
     * 全件検索用メソッド マスタ画面用
     * 
     * @return OrganizationList
     */
    public List<Organization> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param organization organization
     * @return true/false
     */
    public boolean updateOne(Organization organization);

    /**
     * １件削除用メソッド
     * 
     * @param organization_cd organization_cd
     * @return true/false
     */
    public boolean deleteOne(String organization_cd);

    /**
     * 条件検索用メソッド
     * 
     * @param organization_name organization_name
     * @param company_cd        company_cd
     * @param start_yearmonth   start_yearmonth
     * @param end_yearmonth     end_yearmonth
     * @param biko              biko
     * @return OrganizationList
     */
    public List<Organization> selectBy(String organization_name, String company_cd, String start_yearmonth, String end_yearmonth, String biko);

}
