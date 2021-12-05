package com.portal.z.common.domain.service;

import org.json.JSONObject;

/**
 * @author RestAPI起動共用サービス
 *
 */
public interface RestSharedService {

    /**
     * @param url 呼び出すAPIのURL（パラメータも含めた状態）
     * @return API処理結果
     */
    public JSONObject restget(String url);

}