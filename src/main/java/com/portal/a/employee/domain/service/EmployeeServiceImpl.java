package com.portal.a.employee.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.model.Employeeattribute;
import com.portal.a.common.domain.model.Employeebelongs;
import com.portal.a.common.domain.model.Organization;
import com.portal.a.common.domain.repository.EmployeeExpMapper;
import com.portal.a.common.domain.repository.EmployeeMapper;
import com.portal.a.common.domain.repository.EmployeeattributeMapper;
import com.portal.a.common.domain.repository.EmployeebelongsMapper;
import com.portal.a.common.domain.repository.OrganizationMapper;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.repository.UserMapper;

/**
 * EmployeeServiceImpl
 *
 */
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeExpMapper employeeExpMapper;

    @Autowired
    EmployeebelongsMapper employeebelongsMapper;

    @Autowired
    EmployeeattributeMapper employeeattributeMapper;

    @Autowired
    OrganizationMapper organizationMapper;
    
    @Autowired
    UserMapper userMapper;

    public List<Employee> selectMany() {
        return employeeMapper.selectMany();
    }

    public List<Employeeattribute> selectManyemployeeattribute() {
        return employeeattributeMapper.selectManyemployeeattribute();
    }

    public List<Organization> selectManyorganization() {
        return organizationMapper.selectManyorganization();
    }

    public Employee selectOne(String employee_id) {
        return employeeMapper.selectOne(employee_id);
    }

    public boolean insertOne(Employee employee, Employeebelongs employeebelongs) {

        // 社員マスタ追加実行
        boolean result_1 = employeeMapper.insertOne(employee);

        // 社員マスタに登録した社員IDを取得する。
        Employee inserted_employee = employeeExpMapper.selectOneByCd(employee.getEmployee_cd());
        employeebelongs.setEmployee_id(inserted_employee.getEmployee_id());

        // 社員所属マスタ追加実行
        boolean result_2 = employeebelongsMapper.insertOne(employeebelongs);

        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateOne(Employee employee, Employeebelongs employeebelongs) {

        // 組織を変更したかどうか確認する
        boolean result_1 = true;
        // 登録されている
        Employeebelongs inserted_employee = employeebelongsMapper.selectOneById(employeebelongs.getEmployee_id());
        if (!inserted_employee.getOrganization_cd().equals(employeebelongs.getOrganization_cd())) {

            // 組織が変更されたときは社員所属マスタを更新する
            result_1 = employeebelongsMapper.updateOne(employeebelongs);
            if (!result_1) {
                // 更新対象が無かったとき
                // 社員IDを取得する
                employeebelongs.setEmployee_id(employee.getEmployee_id());
                // 社員所属マスタ追加実行
                result_1 = employeebelongsMapper.insertOne(employeebelongs);
            }
        }

        boolean result_2 = employeeMapper.updateOne(employee);
        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteOne(String employee_id) {
        // 削除実行（社員所属マスタ、社員マスタ）
        boolean result_1 = employeebelongsMapper.deleteOne(employee_id);
        boolean result_2 = employeeMapper.deleteOne(employee_id);

        if (result_1 == true && result_2 == true) {
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> selectBy(String employee_cd, String employee_name1_last, String mail, String biko) {
        return employeeExpMapper.selectBy(employee_cd, employee_name1_last, mail, biko);
    }
    
    public User selectUserOne(String user_id) {
        return userMapper.selectOne(user_id);
    }
}
