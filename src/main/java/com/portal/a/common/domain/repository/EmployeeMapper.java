package com.portal.a.common.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.portal.a.common.domain.model.Employee;

/**
 * EmployeeMapper
 *
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 登録用メソッド
     * 
     * @param employee employee
     * @return true/false
     */
    public boolean insertOne(Employee employee);

    /**
     * １件検索用メソッド
     * 
     * @param employee_id employee_id
     * @return Employee
     */
    public Employee selectOne(String employee_id);

    /**
     * 全件検索用メソッド
     * 
     * @return EmployeeList
     */
    public List<Employee> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param employee employee
     * @return true/false
     */
    public boolean updateOne(Employee employee);

    /**
     * １件削除用メソッド
     * 
     * @param employee employee
     * @return true/false
     */
    public boolean deleteOne(String employee);

    /**
     * 条件検索用メソッド
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param mail                mail
     * @param biko                biko
     * @return EnvList
     */
    public List<Employee> selectBy(String employee_cd, String employee_name1_last, String mail, String biko);
}
