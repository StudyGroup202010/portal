package com.portal.a.kbn.domain.model;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 汎用区分マスタ情報のForm
 */
@Data
public class KbnForm implements Serializable {

    // 汎用区分マスタのテーブル情報
    // 1 区分ID     kbn_id      character varying(50)  Not NULL
    // 2 区分値     kbn_txt     character varying(100) Not NULL
    // 3 区分名     kbn_name    character varying(100) Not NULL
    // 4 備考       biko        character varying(100)
    // 5 有効フラグ enabled_flg boolean                Not NULL true
    // 6 表示順     disp_order  smallint               Not NULL 0

    /**
     * シリアライズ
     */
    private static final long serialVersionUID = 1L;

    /**
     * チェック順のインターフェース1
     */
    public interface Check1 {
    }

    /**
     * チェック順のインターフェース2
     */
    public interface Check2 {
    }

    /**
     * チェック順のインターフェース3
     */
    public interface Check3 {
    }

    /**
     * チェック順序のグループシーケンス
     * 順序1…必須入力・型チェック
     * 順序2…その他のチェック
     */
    @GroupSequence({ Check1.class, Check2.class, Check3.class })
    public interface All {
    }

    // ■区分ID
    // 必須入力
    @NotBlank(groups = { Check1.class }, message = "{require_check}")
    // 入力可能文字種：英数記号のみ
    @Pattern(groups = { Check2.class }, regexp = "^[-_0-9a-zA-Z]+$", message = "{Alphanumericsymbols_check}")
    // 文字数
    @Size(groups = { Check2.class }, min = 1, max = 50, message = "{length_check_3}")
    private String kbn_id;

    // ■区分値
    // 必須入力
    @NotBlank(groups = { Check1.class }, message = "{require_check}")
    // 文字数
    @Size(groups = { Check2.class }, min = 1, max = 100, message = "{length_check_3}")
    private String kbn_txt;

    // ■区分名
    // 必須入力
    @NotBlank(groups = { Check1.class }, message = "{require_check}")
    // 文字数
    @Size(groups = { Check2.class }, min = 1, max = 100, message = "{length_check_3}")
    private String kbn_name;

    // ■備考
    // 文字数
    // ※未入力はエラーにしない
    @Size(groups = { Check2.class }, min = 0, max = 100, message = "{length_check_3}")
    private String biko;

    // ■有効フラグ (DBと同じboolean)
    // ラジオボタンなので必ず値が入るためチェックなし。(DBでデフォルトがTrue)になる。
    private boolean enabled_flg;

    // ■表示順
    // 下記のチェックの前にtypemismatchを利用して型チェックしている。
    // 範囲チェック(0から100) 未入力時はデフォルト値で0が入る
    @Min(groups = { Check2.class }, message = "{min_check}", value = 0)
    @Max(groups = { Check2.class }, message = "{max_check}", value = 100)
    private Integer disp_order;

}
