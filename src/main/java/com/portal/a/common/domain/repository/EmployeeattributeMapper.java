package com.portal.a.common.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.portal.a.common.domain.model.Employeeattribute;

/**
 * EmployeeattributeMapper
 *
 */
@Mapper
public interface EmployeeattributeMapper {

    /**
     * 全件検索用メソッド
     * 
     * @return EmployeeattributeList
     */
    public List<Employeeattribute> selectManyemployeeattribute();

}
