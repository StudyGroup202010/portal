package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.Env;

/**
 * 環境マスタ用共通Service
 *
 */
public interface EnvSharedService {

    /**
     * selectIntOne<BR>
     * 
     * 環境マスタに数値を登録している項目用の取得処理。<BR>
     * 数値がセットされているかどうかのチェックも行なっている。
     * 
     * @param env_id env_id
     * @return 数値の場合は値を返す。数値で無い場合はnull
     */
    public Env selectIntOne(String env_id);

}
