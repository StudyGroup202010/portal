package com.portal.a.employee.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.model.Employeeattribute;
import com.portal.a.common.domain.model.Employeebelongs;
import com.portal.a.common.domain.model.Organization;
import com.portal.z.common.domain.model.User;

/**
 * EmployeeService
 *
 */
public interface EmployeeService {

    /**
     * 全件取得用メソッド（社員マスタ）.
     * 
     * @return EmployeeList
     */
    public List<Employee> selectMany();

    /**
     * 全件取得用メソッド（社員属性マスタ）.
     * 
     * @return EmployeeattributeList
     */
    public List<Employeeattribute> selectManyemployeeattribute();

    /**
     * 全件取得用メソッド（組織マスタ）.
     * 
     * @return OrganizationList
     */
    public List<Organization> selectManyorganization();

    /**
     * １件取得用メソッド.
     * 
     * @param employee_id employee_id
     * @return employee
     */
    public Employee selectOne(String employee_id);

    /**
     * 登録用メソッド
     * 
     * @param employee        employee
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean insertOne(Employee employee, Employeebelongs employeebelongs);

    /**
     * １件更新用メソッド.
     * 
     * @param employee        employee
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean updateOne(Employee employee, Employeebelongs employeebelongs);

    /**
     * １件削除用メソッド.
     * 
     * @param employee_id employee_id
     * @return true/false
     */
    public boolean deleteOne(String employee_id);

    /**
     * 条件検索用メソッド.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param mail                mail
     * @param biko                biko
     * @return EmployeeList
     */
    public List<Employee> selectBy(String employee_cd, String employee_name1_last, String mail, String biko);

    /**
     * １件取得用メソッド.
     * 
     * @param user_id user
     * @return User
     */
    public User selectUserOne(String user_id);

    /**
     * ユーザマスタ存在確認用メソッド.
     * 
     * @param employee_id employee_id
     * @return User
     */
    public User selectByEmployeeid(String employee_id);
}
