package com.portal.b.common.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.portal.b.common.domain.model.Certification;

/**
 * CertificationMapper
 *
 */
@Mapper
public interface CertificationMapper {

    /**
     * 全件検索用メソッド
     * 
     * @return CertificationList
     */
    public List<Certification> selectMany();
}
