package com.portal.a.kbn.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.a.common.domain.model.Kbn;
import com.portal.a.kbn.domain.model.KbnForm;
import com.portal.a.kbn.domain.model.KbnForm.All;
import com.portal.a.kbn.domain.model.KbnListCsvView;
import com.portal.a.kbn.domain.model.KbnListXlsxView;
import com.portal.a.kbn.domain.service.KbnService;
import com.portal.z.common.domain.util.MassageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 汎用区分マスタ用のController
 * 
 */
@Controller
@Slf4j
public class kbnController {

    @Autowired
    private KbnService kbnService;

    @Autowired
    private MassageUtils messageUtils;

    /**
     * ラジオボタンの初期化メソッド.
     */
    private Map<String, String> initRadioEnabled() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 有効、無効をMapに格納
        radio.put("無効", "false");
        radio.put("有効", "true");

        return radio;
    }

    /**
     * 汎用区分マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 汎用区分マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/kbnList")
    public String getKbnList(Model model) {

        // formを登録
        KbnForm form = new KbnForm();
        model.addAttribute("kbnForm", form);

        // コンテンツ部分に汎用区分マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/kbnList :: kbnList_contents");

        // 汎用区分マスタ一覧の生成
        List<Kbn> kbnList = kbnService.selectMany();

        // Modelに汎用区分リストを登録
        model.addAttribute("kbnList", kbnList);

        // データ件数を取得
        int count = kbnList.size();
        model.addAttribute("kbnListCount", count);

        return "z/homeLayout";
    }

    /**
     * 汎用区分マスタ一覧画面の検索用処理.<br>
     * 
     * 画面から入力した検索条件で汎用区分マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/kbnList", params = "selectby")
    public String getKbnListByKbnid(@ModelAttribute KbnForm form, BindingResult bindingResult, Model model) {

        // コンテンツ部分に汎用区分マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/kbnList :: kbnList_contents");

        Kbn inpKbn = new Kbn();

        BeanUtils.copyProperties(form, inpKbn);

        // 汎用区分マスタ情報を取得
        List<Kbn> kbnList = kbnService.selectBy(inpKbn);
        
        // Modelに汎用区分マスタリストを登録
        model.addAttribute("kbnList", kbnList);

        // データ件数を登録
        int count = kbnList.size();
        model.addAttribute("kbnListCount", count);

        return "z/homeLayout";
    }

    /**
     * 汎用区分マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 汎用区分情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/kbnUpdate")
    public String getSignUp(@ModelAttribute KbnForm form, Model model) {

        // コンテンツ部分に汎用区分マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/kbnUpdate :: kbnUpdate_contents");
        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioEnabled", initRadioEnabled());
        // 汎用区分マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 汎用区分マスタ新規登録用の処理(POSTメソッド)
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/kbnUpdate")
    public String postSignUp(@ModelAttribute @Validated(All.class) KbnForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、汎用区分マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、汎用区分マスタ登録画面に戻ります
            return getSignUp(form, model);
        }

        // 汎用区分マスタinsert用変数
        Kbn kbn = new Kbn();
        BeanUtils.copyProperties(form, kbn);

        // 汎用区分マスタ登録処理(kbn)
        try {
            boolean result = kbnService.insertOne(kbn);

            // 汎用区分マスタ登録結果の判定
            if (result == true) {
                model.addAttribute("result", "登録成功");
                log.info("登録成功");
            } else {
                model.addAttribute("result", "登録失敗");
                log.error("登録失敗");
            }
        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生したとき。
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", messageUtils.getMsg(messageKey, new String[] { kbn.getKbn_id() }));
            return getSignUp(form, model);
        }

        // 汎用区分マスタ一覧画面を表示
        return getKbnList(model);
    }

    /**
     * 汎用区分マスタ登録画面の戻る処理.<BR>
     * 
     * 汎用区分マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getKbnList(model)
     */
    @PostMapping(value = "/kbnUpdate", params = "back")
    public String postKbnUpdateback(Model model) {
        // 汎用区分マスタ一覧画面を表示
        return getKbnList(model);
    }

    /**
     * 汎用区分マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 汎用区分マスタ詳細画面を表示する。
     * 
     * @param form    入力用form
     * @param model   モデル
     * @param kbn_id  区分ID
     * @param kbn_txt 区分値
     * @param result  BindingResult
     * @return "z/homeLayout" の文字列
     */
    @GetMapping("/kbnDetail/{id}_{txt}")
    public String getKbnDetail(@ModelAttribute KbnForm form, Model model, @PathVariable("id") String kbn_id,
            @PathVariable("txt") String kbn_txt, BindingResult result) {

        // コンテンツ部分に汎用区分マスタ詳細を表示するための文字列(フラグメント)を登録
        model.addAttribute("contents", "a/kbnDetail :: kbnDetail_contents");

//        // エラーで戻ってきたときは処理を変える
        if (!result.hasErrors()) {

            // 汎用区分idのチェック
            if (StringUtils.isNotBlank(kbn_id) && StringUtils.isNotBlank(kbn_txt)) {

                Kbn inpKbn = new Kbn();
                inpKbn.setKbn_id(kbn_id);
                inpKbn.setKbn_txt(kbn_txt);
                // 汎用区分マスタ情報を取得
                Kbn kbn = kbnService.selectOne(inpKbn);

                // Kbnクラスをフォームクラスに変換
                BeanUtils.copyProperties(kbn, form);
            }
        }
        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioEnabled", initRadioEnabled());

        return "z/homeLayout";
    }

    /**
     * 汎用区分マスタ詳細画面の汎用区分マスタ更新用処理.<BR>
     * 
     * 画面から入力した汎用区分マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getKbnList(model)
     */
    @PostMapping(value = "/kbnDetail", params = "update")
    public String postKbnDetailUpdate(@ModelAttribute @Validated(All.class) KbnForm form, BindingResult bindingResult,
            Model model) {

        // メモ：KbnDetail.htmlにname=updateの指定があるので、
        // @PostMappingでvalue = "/kbnDetail"を指定している

        // 入力チェックに引っかかった場合、汎用区分マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、汎用区分マスタ詳細画面に戻ります
            return getKbnDetail(form, model, form.getKbn_id(), form.getKbn_txt(), bindingResult);
        }

        // Kbnインスタンスの生成
        Kbn kbn = new Kbn();
        // formからkbnに詰め替え
        BeanUtils.copyProperties(form, kbn);

        // 更新実行
        boolean result = kbnService.updateOne(kbn);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 汎用区分マスタ一覧画面を表示
        return getKbnList(model);
    }

    /**
     * 汎用区分マスタ詳細画面の汎用区分マスタ削除用処理.<BR>
     * 
     * 画面から入力した汎用区分マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getKbnList(model)
     */
    @PostMapping(value = "/kbnDetail", params = "delete")
    public String postKbnDetailDelete(@ModelAttribute KbnForm form, Model model) {

        // Kbnインスタンスの生成
        Kbn kbn = new Kbn();
        // formからkbnに詰め替え
        BeanUtils.copyProperties(form, kbn);

        // 削除実行
        boolean result = kbnService.deleteOne(kbn);

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 汎用区分マスタ一覧画面を表示
        return getKbnList(model);
    }

    /**
     * 汎用区分マスタ詳細画面の戻る処理.<BR>
     * 
     * 汎用区分マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getKbnList(model)
     */
    @PostMapping(value = "/kbnDetail", params = "back")
    public String postKbnDetailback(Model model) {
        // 汎用区分マスタ一覧画面を表示
        return getKbnList(model);
    }

    /**
     * 汎用区分マスタ一覧のCSV出力用処理.<br>
     * 
     * 汎用区分マスタ一覧のCSVファイルを出力する。
     * 
     * @param model モデル
     * @return ResponseEntity(bytes, header, HttpStatus.OK)
     */
    @GetMapping("/kbnList/csv")
    public KbnListCsvView getKbnListCsv(KbnListCsvView model) {

        // 汎用区分マスタ一覧の生成
        List<Kbn> kbnList = kbnService.selectMany();

        // Modelに汎用区分リストを登録
        model.addStaticAttribute("kbnList", kbnList);

        return model;
    }

    /**
     * 汎用区分マスタ一覧のExcel出力用処理.<br>
     * 
     * 汎用区分マスタ一覧のEXCELファイルを出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/kbnList/excel")
    public KbnListXlsxView excel(KbnListXlsxView model) {

        // 汎用区分マスタ一覧の生成
        List<Kbn> kbnList = kbnService.selectMany();

        // Modelに汎用区分リストを登録
        model.addStaticAttribute("kbnList", kbnList);

        // データ件数を取得
        int count = kbnList.size();
        model.addStaticAttribute("kbnListCount", count);

        return model;
    }

}