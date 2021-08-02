package com.portal.a.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.portal.a.common.domain.model.Employeebelongs;

/**
 * EmployeebelongsMapper
 *
 */
@Mapper
public interface EmployeebelongsMapper {

    /**
     * 登録用メソッド
     * 
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean insertOne(Employeebelongs employeebelongs);

    /**
     * １件検索用メソッド(employee_id)
     * 
     * @param employee_id     employee_id
     * @return Employeebelongs
     */
    public Employeebelongs selectOneById(String employee_id);

    /**
     * １件更新用メソッド
     * 
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean updateOne(Employeebelongs employeebelongs);

    /**
     * １件削除用メソッド
     * 
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean deleteOne(String employeebelongs);

}
