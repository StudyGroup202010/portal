package com.portal.z.common.domain.service;

import com.portal.z.common.domain.model.Env;

/**
 * EnvSharedService
 *
 */
public interface EnvSharedService {

    /**
     * selectIntOne<BR>
     * 
     * @param env_id env_id
     * @return 数値の場合は値を返す。数値で無い場合はnull
     */
    public Env selectIntOne(String env_id);

}
