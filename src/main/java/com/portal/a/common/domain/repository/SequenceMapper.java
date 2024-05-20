package com.portal.a.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import com.portal.a.common.domain.model.Sequence;

/**
 * SequenceMapper
 *
 */
@Mapper
public interface SequenceMapper {

    /**
     * 登録用メソッド
     * 
     * @param sequence sequence
     * @return true/false
     */
    public boolean insertSequenceOne();

    /**
     * １件検索用メソッド
     * 
     * @return Sequence
     */
    public Sequence selectSequenceOne();

    /**
     * １件削除用メソッド
     * 
     * @param id id
     * @return true/false
     */
    public boolean deleteSequenceOne(String id);
}
