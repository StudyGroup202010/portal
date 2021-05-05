package com.portal.a.env.domain.model;

import lombok.Data;

/**
 * 環境マスタ情報の検索用Form
 */
@Data
public class SelectEnvForm {
    private String env_id; // 環境ID
    private String env_kbn; // 環境区分
    private String env_txt; // 環境値
    private String biko; // 備考
}
