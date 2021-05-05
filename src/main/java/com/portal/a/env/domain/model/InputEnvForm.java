package com.portal.a.env.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 環境マスタ情報の入力用Form
 */
@Data
public class InputEnvForm {
    // 必須入力
    @NotBlank(message = "{require_check}")
    private String env_id; // 環境ID

    private String env_kbn; // 環境区分

    @Min(value = 0, message = "{min_check}")
    @Max(value = 100, message = "{max_check}")
    private int env_num; // 環境連番

    // 必須入力
    @NotBlank(message = "{require_check}")
    private String env_txt; // 環境値

    private String biko; // 備考
}
