package com.portal.a.common.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.portal.a.common.domain.model.Employee;

/**
 * EmployeeMapper
 *
 */
@Mapper
public interface EmployeeExpMapper {
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
