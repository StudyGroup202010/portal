package com.portal.z.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.Env;

@Mapper
public interface EnvMapper {

    // 登録用メソッド
    public boolean insertOne(Env env);

    // １件検索用メソッド
    public Env selectOne(String env_id);

    // 全件検索用メソッド
    public List<Env> selectMany();

    // １件更新用メソッド
    public boolean updateOne(Env env);

    // １件削除用メソッド
    public boolean deleteOne(String env_id);
}
