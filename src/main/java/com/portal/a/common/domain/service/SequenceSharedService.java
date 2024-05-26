package com.portal.a.common.domain.service;

import com.portal.a.common.domain.model.Sequence;

/**
 * シーケンス用共通Service
 *
 */
public interface SequenceSharedService {

    /**
     * １件取得用メソッド.
     * 
     * @return sequence
     */
    public Sequence selectOne();

}
