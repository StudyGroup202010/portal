package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 環境マスタ
 *
 */
@Data
public class Env {
    private String env_id; // 環境ＩＤ
    private String env_kbn; // 環境区分
    private int env_num; // 環境連番
    private String env_txt; // 環境値
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}