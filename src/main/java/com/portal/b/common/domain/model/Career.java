package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 業務経歴情報
 *
 */
@Data
public class Career {
    private String employee_id;// 社員ID
    private String employee_cd;// 社員CD
    private String employee_name1_last;// 社員名漢字（姓）
    private String employee_name1_first;// 社員名漢字（名）
    private String certification_no; // 経歴番号
    private String disp_order; // 表示順
    private String start_yearmonth;// 開始年月
    private String end_yearmonth;// 終了年月
    private String business_content;// 業務内容
    private String technology_Lang; // 技術区分_開発言語
    private String technology_OS; // 技術区分_OS
    private String technology_DB; // 技術区分_DB
    private String process_name; // 工程名
    private String process_name_short; // 工程名略
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}