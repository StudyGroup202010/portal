package com.portal.z.common.domain.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author RestAPI起動共用サービス
 *
 */
@Component
public class RestSharedServiceImpl implements RestSharedService {

    public String restget(String url) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
