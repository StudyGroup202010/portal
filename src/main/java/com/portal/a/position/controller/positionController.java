package com.portal.a.position.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.a.common.domain.model.Position;
import com.portal.a.position.domain.model.CreateOrder;
import com.portal.a.position.domain.model.InputPositionForm;
import com.portal.a.position.domain.model.SelectPositionForm;
import com.portal.a.position.domain.model.UpdateOrder;
import com.portal.a.position.domain.service.PositionService;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 役職マスタ用のController
 * 
 */
@Controller
@Slf4j
public class positionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 役職マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 役職マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/positionList")
    public String getPositionList(Model model) {

        // 検索条件のformを登録
        SelectPositionForm form = new SelectPositionForm();
        model.addAttribute("selectPositionForm", form);

        // コンテンツ部分に役職マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/positionList :: positionList_contents");

        // 役職マスタ一覧の生成
        List<Position> positionList = positionService.selectMany();

        // Modelにユーザーリストを登録
        model.addAttribute("positionList", positionList);

        // データ件数を取得
        int count = positionList.size();
        model.addAttribute("positionListCount", count);

        return "z/homeLayout";
    }

    /**
     * 役職マスタ一覧画面のユーザー検索用処理.<br>
     * 
     * 画面から入力した検索条件で役職マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/positionList", params = "selectby")
    public String getPositionListByCompanyid(@ModelAttribute SelectPositionForm form, BindingResult bindingResult,
            Model model) {

        // コンテンツ部分に役職マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/positionList :: positionList_contents");

        // 役職マスタ情報を取得
        List<Position> positionList = positionService.selectBy(form.getPosition_name(), form.getStart_yearmonth(), form.getEnd_yearmonth(), form.getBiko());

        // Modelに役職マスタリストを登録
        model.addAttribute("positionList", positionList);

        // データ件数を登録
        int count = positionList.size();
        model.addAttribute("positionListCount", count);

        return "z/homeLayout";
    }

    /**
     * 役職マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 役職情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/positionUpdate")
    public String getSignUp(@ModelAttribute InputPositionForm form, Model model) {

        // コンテンツ部分に役職マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/positionUpdate :: positionUpdate_contents");

        // 役職マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 役職マスタ登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/positionUpdate")
    public String postSignUp(@ModelAttribute @Validated(CreateOrder.class) InputPositionForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、役職マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、役職マスタ登録画面に戻ります
            return getSignUp(form, model);
        }
        
        // 年月チェック（start_yearmonth）
        if (form.getStart_yearmonth() != null && !form.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、役職マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getSignUp(form, model);
            }
        }
        
        // 年月チェック（end_yearmonth）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、役職マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "最終年月" }));
                return getSignUp(form, model);
            }
        }

        // 年月チェック(開始年月 <= 最終年月）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(DateUtils.getDateFromString(form.getStart_yearmonth() + "01").atStartOfDay(),
            		DateUtils.getDateFromString(form.getEnd_yearmonth() + "01").atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "開始年月：" + form.getStart_yearmonth(), "最終年月：" + form.getEnd_yearmonth() }));
                return getSignUp(form, model);
            }
        }
        
        
        // 役職マスタinsert用変数
        Position position = new Position();

        position.setPosition_cd(form.getPosition_cd()); // 役職CD
        position.setPosition_name(form.getPosition_name()); // 役職名
        position.setStart_yearmonth(form.getStart_yearmonth()); // 開始年月
        position.setEnd_yearmonth(form.getEnd_yearmonth()); // 最終年月
        position.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        position.setInsert_user(user_auth.getUsername()); // 作成者

        // 役職マスタ登録処理(position)
        try {
            boolean result = positionService.insertOne(position);

            // 役職マスタ登録結果の判定
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
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { position.getPosition_cd() }));
            return getSignUp(form, model);
        }

        // 役職マスタ一覧画面を表示
        return getPositionList(model);
    }

    /**
     * 役職マスタ登録画面の戻る処理.<BR>
     * 
     * 役職マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getPositionList(model)
     */
    @PostMapping(value = "/positionUpdate", params = "back")
    public String postPositionUpdateback(Model model) {
        // 役職マスタ一覧画面を表示
        return getPositionList(model);
    }

    /**
     * 役職マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 役職マスタ詳細画面を表示する。
     * 
     * @param form       入力用form
     * @param model      モデル
     * @param position_cd 詳細情報を表示する
     * @return z/homeLayout
     */
    @GetMapping("/positionDetail/{id}")
    public String getPositionDetail(@ModelAttribute InputPositionForm form, Model model,
            @PathVariable("id") String position_cd) {

        // コンテンツ部分に役職マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/positionDetail :: positionDetail_contents");

        // 役職CDのチェック
        if (position_cd != null && StrUtils.getStrLength(position_cd) > 0) {

            // 役職マスタ情報を取得
        	Position position = positionService.selectOne(position_cd);

            // Positionクラスをフォームクラスに変換
            form.setPosition_cd(position.getPosition_cd()); // 役職CD
            form.setPosition_name(position.getPosition_name()); // 役職名
            form.setStart_yearmonth(position.getStart_yearmonth()); // 開始年月
            form.setEnd_yearmonth(position.getEnd_yearmonth()); // 最終年月
            form.setBiko(position.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputPositionForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 役職マスタ詳細画面の役職マスタ更新用処理.<BR>
     * 
     * 画面から入力した役職マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getPositionList(model)
     */
    @PostMapping(value = "/positionDetail", params = "update")
    public String postPositionDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputPositionForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、役職マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、役職マスタ詳細画面に戻ります
            return getPositionDetail(form, model, "");
        }
        
        // 年月チェック（start_yearmonth）
        if (form.getStart_yearmonth() != null && !form.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、役職マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getPositionDetail(form, model, "");
            }
        }
        
        // 年月チェック（end_yearmonth）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、役職マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "最終年月" }));
                return getPositionDetail(form, model, "");
            }
        }        

        // 年月チェック(開始年月 <= 最終年月）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(DateUtils.getDateFromString(form.getStart_yearmonth() + "01").atStartOfDay(),
            		DateUtils.getDateFromString(form.getEnd_yearmonth() + "01").atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "開始年月：" + form.getStart_yearmonth(), "最終年月：" + form.getEnd_yearmonth() }));
                return getPositionDetail(form, model, "");
            }
        }
        
        // Positionインスタンスの生成
        Position position = new Position();

        position.setPosition_cd(form.getPosition_cd()); // 役職CD
        position.setPosition_name(form.getPosition_name()); // 役職名
        position.setStart_yearmonth(form.getStart_yearmonth()); // 開始年月
        position.setEnd_yearmonth(form.getEnd_yearmonth()); // 最終年月
        position.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        position.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = positionService.updateOne(position);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 役職マスタ一覧画面を表示
        return getPositionList(model);
    }

    /**
     * 役職マスタ詳細画面の役職マスタ削除用処理.<BR>
     * 
     * 画面から入力した役職マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getPositionList(model)
     */
    @PostMapping(value = "/positionDetail", params = "delete")
    public String postPositionDetailDelete(@ModelAttribute InputPositionForm form, Model model) {

        // 削除実行
        boolean result = positionService.deleteOne(form.getPosition_cd());

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 役職マスタ一覧画面を表示
        return getPositionList(model);
    }

    /**
     * 役職マスタ詳細画面の戻る処理.<BR>
     * 
     * 役職マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getPositionList(model)
     */
    @PostMapping(value = "/positionDetail", params = "back")
    public String postPositionDetailback(Model model) {
        // 役職マスタ一覧画面を表示
        return getPositionList(model);
    }
}