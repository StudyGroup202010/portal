package com.portal.b.certification.domain.service;

import java.util.List;
import com.portal.b.common.domain.model.Certification;

/**
 * CertificationService
 *
 */
public interface CertificationService {

    // 資格マスタ
    /**
     * 全件取得用メソッド（資格マスタ）.
     * 
     * 一覧表は資格マスタから作ります。
     * 
     * @return CertificationList
     */
    public List<Certification> selectMany();

}
