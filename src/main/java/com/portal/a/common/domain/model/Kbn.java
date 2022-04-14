package com.portal.a.common.domain.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

//TableInfo
//1   区分id     kbn_id      character varying(50)   Yes 
//2   区分値     kbn_txt     character varying(100)  Yes 
//3   区分名     kbn_name    character varying(100)  Yes 
//4   備考       biko        character varying(100)      
//5   有効フラグ enabled_flg boolean                 Yes true
//6   表示順     disp_order  smallint                Yes 0

/**
 * 汎用マスタのデータオブジェクト
 */
@Data
public class Kbn {
    private String kbn_id; // 区分id
    private String kbn_txt; // 区分値
    private String kbn_name; // 区分名
    private String biko; // 備考
    private boolean enabled_flg; // 有効フラグ(DBと同じboolean)
    private Integer disp_order; // 表示順

    /**
     * str_enabled_flgの名前で文字列の有効フラグを取得する。 EXCEL出力、CSV出力用。 ユーザーマスタに合わせて大文字で出力する。
     * 
     * @return 文字列の"true"/"false"
     */
    public String getStr_enabled_flg() {
        return StringUtils.upperCase(String.valueOf(enabled_flg));
    }

    /**
     * str_enabled_flgの名前で"true"か"false"の文字列で 有効フラグのBooleanを設定する。
     * 
     * @param value 文字列のBoolean("true"か"false"を想定)
     */
    public void setStr_enabled_flg(String value) {

        String tempValue = StringUtils.upperCase(value);

        if (tempValue == null) {
            this.enabled_flg = false;
        } else {
            if ("TRUE".equals(tempValue)) {
                this.enabled_flg = true;
            } else {
                this.enabled_flg = false;
            }
        }

    }

}
