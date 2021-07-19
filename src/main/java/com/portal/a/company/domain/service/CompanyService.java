package com.portal.a.company.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Company;

/**
 * CompanyService
 *
 */
public interface CompanyService {

    /**
     * 全件取得用メソッド.
     * 
     * @return CompanyList
     */
    public List<Company> selectMany();

    /**
     * １件取得用メソッド.
     * 
     * @param company_cd company_cd
     * @return company
     */
    public Company selectOne(String company_cd);

    /**
     * 登録用メソッド
     * 
     * @param company company
     * @return true/false
     */
    public boolean insertOne(Company company);

    /**
     * １件更新用メソッド.
     * 
     * @param company company
     * @return true/false
     */
    public boolean updateOne(Company company);

    /**
     * １件削除用メソッド.
     * 
     * @param company_cd company_cd
     * @return true/false
     */
    public boolean deleteOne(String company_cd);

    /**
     * 条件検索用メソッド.
     * 
     * @param company_name company_name
     * @param biko         biko
     * @return CompanyList
     */
    public List<Company> selectBy(String company_name, String biko);
}
