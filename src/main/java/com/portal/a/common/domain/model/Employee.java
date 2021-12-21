package com.portal.a.common.domain.model;

import java.sql.Timestamp;
import java.sql.Date;
import lombok.Data;

/**
 * 社員マスタ
 *
 */
@Data
public class Employee {
    private String employee_id;// 社員ID
    private String employee_cd;// 社員CD
    private String employee_name1_last;// 社員名漢字（姓）
    private String employee_name1_first;// 社員名漢字（名）
    private String employee_name2_last;// 社員名カナ（姓）
    private String employee_name2_first;// 社員名カナ（名）
    private String gender_kbn;// 性別区分
    private String postcode;// 郵便番号
    private String prefcode;// 都道府県名CD
    private String pref_name1;// 都道府県名１
    private String pref_name2;// 都道府県名２
    private String citycode;// 市区町村名CD
    private String city_name1;// 市区町村名１
    private String city_name2;// 市区町村名２
    private String streetaddress1;// 住所１
    private String streetaddress2;// 住所２
    private Date birthday;// 生年月日
    private String nearest_station_code;// 最寄駅CD
    private String nearest_station_name;// 最寄駅名
    private String mail;// メールアドレス
    private Date joined_date;// 入社日
    private Date leave_date;// 退社日
    private String leave;// 退社済
    private String employeeattribute_id;// 社員属性ID
    private String employeeattribute_name;// 社員属性名
    private String organization_cd;// 組織CD
    private String organization_name;// 組織名
    private String start_yearmonth;// 開始年月
    private String biko; // 備考
    private String insert_user; // 作成者
    private Timestamp insert_date; // 作成日時
    private String update_user; // 更新者
    private Timestamp update_date; // 更新日時
}