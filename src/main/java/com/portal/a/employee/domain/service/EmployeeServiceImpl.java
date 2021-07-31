package com.portal.a.employee.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.model.Employeeattribute;
import com.portal.a.common.domain.repository.EmployeeMapper;
import com.portal.a.common.domain.repository.EmployeeattributeMapper;

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
    EmployeeattributeMapper employeeattributeMapper;

    public List<Employee> selectMany() {
        return employeeMapper.selectMany();
    }
    
    public List<Employeeattribute> selectManyemployeeattribute() {
        return employeeattributeMapper.selectManyemployeeattribute();
    }

    public Employee selectOne(String employee_id) {
        return employeeMapper.selectOne(employee_id);
    }

    public boolean insertOne(Employee employee) {
        return employeeMapper.insertOne(employee);
    }

    public boolean updateOne(Employee employee) {
        return employeeMapper.updateOne(employee);
    }

    public boolean deleteOne(String employee_id) {
        return employeeMapper.deleteOne(employee_id);
    }

    public List<Employee> selectBy(String employee_cd, String employee_name1_last, String mail, String biko) {
        return employeeMapper.selectBy(employee_cd, employee_name1_last, mail, biko);
    }
}
