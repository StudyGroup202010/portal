package com.portal.b.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * ProcessMapper
 *
 */
@Mapper
public interface ProcessMapper {

    /**
     * 登録用メソッド
     * 
     * @param process process
     * @return true/false
     */
    public boolean insertOne(Process process);

    /**
     * １件検索用メソッド
     * 
     * @param process_id process_id
     * @return Process
     */
    public Process selectOne(String process_id);

    /**
     * 全件検索用メソッド
     * 
     * @return ProcessList
     */
    public List<Process> selectMany();

    /**
     * １件更新用メソッド
     * 
     * @param process process
     * @return true/false
     */
    public boolean updateOne(Process process);

    /**
     * １件削除用メソッド
     * 
     * @param process_id process_id
     * @return true/false
     */
    public boolean deleteOne(String process_id);
}
