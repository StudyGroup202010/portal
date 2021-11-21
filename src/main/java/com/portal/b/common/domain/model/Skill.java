package com.portal.b.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * スキル情報
 *
 */
@Data
public class Skill {
    private String employee_id;// 社員ID
    private String employee_cd;// 社員CD
    private String employee_name1_last;// 社員名漢字（姓）
    private String employee_name1_first;// 社員名漢字（名）
    private String organization_cd;// 組織CD
    private String organization_name;// 組織名
    private String employee_id_skill;// スキル情報社員ID
    private String final_education;// 最終学歴
    private String department;// 学科
    private String graduation_date;// 卒業年月
    private String notices;// 特記事項
    private String certification_name;// 資格
    private String biko; // 備考
    // 業務経歴
    private String disp_order; // 表示順
    private String certification_no; // 経歴番号
    private String start_yearmonth;// 開始年月
    private String end_yearmonth;// 終了年月
    private String business_content;// 業務内容
    private String careerbiko; // 備考
    // 業務経歴技術
    private String technology_Lang; // 技術名（開発言語）
    private String technology_OS; // 技術名（OS）
    private String technology_DB; // 技術名（DB）
    // 業務経歴工程
    private String process_name; // 工程
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}