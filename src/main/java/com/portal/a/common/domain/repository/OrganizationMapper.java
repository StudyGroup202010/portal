package com.portal.a.common.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.portal.a.common.domain.model.Organization;

/**
 * OrganizationMapper
 *
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 全件検索用メソッド
     * 
     * @return OrganizationList
     */
    public List<Organization> selectManyorganization();

}
