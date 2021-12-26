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
     * １件検索用メソッド(employee_id)
     * 
     * @param employee_id employee_idで検索したいときに値をセット
     * @param employee_cd employee_cdで検索したいときに値をセット
     * @param mail        mailで検索したいときに値をセット
     * @return Employee
     */
    public Employee selectOne(String employee_id, String employee_cd, String mail);

    /**
     * 全件検索用メソッド
     * 
     * @param leave_flg 検索結果から退職者を表示したい時に"1"をセットする
     * @return EmployeeList
     */
    public List<Employee> selectMany(String leave_flg);

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
}
