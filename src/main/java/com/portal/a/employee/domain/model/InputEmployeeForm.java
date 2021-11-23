package com.portal.a.employee.domain.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 社員マスタ情報の入力用Form
 */
@Data
public class InputEmployeeForm {
    private String employee_id; // 社員ID
    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Size(min = 1, max = 15, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check_3}")
    @Pattern(regexp = "^[-_0-9a-zA-Z]+$", groups = { ValidCreate2.class,
            ValidUpdate2.class }, message = "{Alphanumericsymbols_check}") // 英数記号であること
    private String employee_cd; // 社員CD

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String employee_name1_last; // 社員名漢字（姓）

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String employee_name1_first; // 社員名漢字（名）

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String employee_name2_last;// 社員名カナ（姓）

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String employee_name2_first;// 社員名カナ（名）

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    private String gender_kbn;// 性別区分

    @Size(max = 7, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check_2}")
    @Pattern(regexp = "[0-9 ０-９]*", groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{numerical_check}") // 数字であること
    private String postcode;// 郵便番号

    @Size(max = 2, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check_2}")
    private String prefcode;// 都道府県名CD

    private String pref_name1;// 都道府県名１

    private String pref_name2;// 都道府県名２

    @Size(max = 3, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check_2}")
    private String citycode;// 市区町村名CD

    private String city_name1;// 市区町村名１

    private String city_name2;// 市区町村名２

    private String streetaddress1;// 住所１

    private String streetaddress2;// 住所２

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;// 生年月日

    @Size(max = 10, groups = { ValidCreate2.class, ValidUpdate2.class }, message = "{length_check_2}")
    private String nearest_station_code;// 最寄駅コード

    private String nearest_station_name;// 最寄駅名

    @NotBlank(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @Email(groups = { ValidCreate2.class, ValidUpdate2.class })
    private String mail;// メールアドレス

    @NotNull(groups = { ValidCreate1.class, ValidUpdate1.class }, message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joined_date;// 入社日

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leave_date;// 退社日

    private String employeeattribute_id;// 社員属性ID

    private String organization_cd;// 組織CD

    private String start_yearmonth;// 開始年月

    private String biko; // 備考
}
