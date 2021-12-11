package com.portal.a.employee.domain.service;

import java.util.List;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.model.Employeeattribute;
import com.portal.a.common.domain.model.Employeebelongs;
import com.portal.a.common.domain.model.Organization;
import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Skill;
import com.portal.z.common.domain.model.User;

/**
 * EmployeeService
 *
 */
public interface EmployeeService {

    // 社員マスタ
    /**
     * 全件取得用メソッド（社員マスタ）.
     * 
     * @return EmployeeList
     */
    public List<Employee> selectMany();

    /**
     * １件取得用メソッド（社員マスタ）.
     * 
     * @param employee_id employee_id
     * @return employee
     */
    public Employee selectOne(String employee_id);

    /**
     * １件更新用メソッド（社員マスタ）.
     * 
     * @param employee        employee
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean updateOne(Employee employee, Employeebelongs employeebelongs);

    /**
     * １件削除用メソッド（社員マスタ）.
     * 
     * @param employee_id employee_id
     * @return true/false
     */
    public boolean deleteOne(String employee_id);

    /**
     * 登録用メソッド（社員マスタ）.
     * 
     * @param employee        employee
     * @param employeebelongs employeebelongs
     * @return true/false
     */
    public boolean insertOne(Employee employee, Employeebelongs employeebelongs);

    /**
     * 条件検索用メソッド（社員マスタ）.
     * 
     * @param employee_cd         employee_cd
     * @param employee_name1_last employee_name1_last
     * @param mail                mail
     * @param biko                biko
     * @return EmployeeList
     */
    public List<Employee> selectBy(String employee_cd, String employee_name1_last, String mail, String biko);

    // 社員属性マスタ
    /**
     * 全件取得用メソッド（社員属性マスタ）.
     * 
     * @return EmployeeattributeList
     */
    public List<Employeeattribute> selectManyemployeeattribute();

    // 組織マスタ
    /**
     * 全件取得用メソッド（組織マスタ）.
     * 
     * @return OrganizationList
     */
    public List<Organization> selectManyorganization();

    // ユーザマスタ
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

    // スキル情報
    /**
     * スキル情報存在確認用メソッド.
     * 
     * @param employee_id employee_id
     * @return Skill
     */
    public Skill selectSkillOne(String employee_id);

    // 業務経歴
    /**
     * 業務経歴条件検索用メソッド.
     * 
     * @param employee_id employee_id
     * @return CareerList
     */
    public List<Career> selectCareerBy(String employee_id);
}
