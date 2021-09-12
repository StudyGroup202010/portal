package com.portal.b.skill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.portal.a.employee.domain.model.UpdateOrder;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.skill.domain.model.InputSkillForm;
import com.portal.b.skill.domain.model.SelectSkillForm;
import com.portal.b.skill.domain.service.SkillService;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * スキル管理システムのController
 * 
 */
@Controller
@Slf4j
public class skillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * スキル一覧画面のGET用メソッド.
     * 
     * @param model Modelクラス
     * @return 画面のテンプレート名
     */
    @GetMapping("/skillList")
    public String getSkillList(Model model) {

        SelectSkillForm form = new SelectSkillForm();
        model.addAttribute("selectSkillForm", form);

        // コンテンツ部分にスキル一覧画面を表示するための文字列を登録
        model.addAttribute("contents", "b/skillList :: skillList_contents");
        // 検索条件のformを登録

        // スキル情報一覧の生成
        List<Skill> skillList = skillService.selectMany();

        // Modelにスキルリストを登録
        model.addAttribute("skillList", skillList);

        // データ件数を取得
        int count = skillList.size();
        model.addAttribute("skillListCount", count);

        return "z/homeLayout";
    }

    /**
     * スキル一覧画面のスキル検索用処理.<br>
     * 
     * 画面から入力した検索条件でスキル情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/skillList", params = "selectby")
    public String getSkillListByEmployeecd(@ModelAttribute SelectSkillForm form, BindingResult bindingResult,
            Model model) {

        // コンテンツ部分にスキル一覧画面を表示するための文字列を登録
        model.addAttribute("contents", "b/skillList :: skillList_contents");
        // 検索条件のformを登録

        // スキル情報を取得
        List<Skill> skillList = skillService.selectBy(form.getEmployee_cd(), form.getEmployee_name1_last(),
                form.getBiko());

        // Modelにスキルリストを登録
        model.addAttribute("skillList", skillList);

        // データ件数を登録
        int count = skillList.size();
        model.addAttribute("skillListCount", count);

        return "z/homeLayout";
    }

    /**
     * スキル情報詳細画面のGETメソッド用処理.<BR>
     * 
     * スキル情報詳細画面を表示する。
     * 
     * @param form        入力用form
     * @param model       モデル
     * @param employee_id 詳細情報を表示するemployee_id
     * @return z/homeLayout
     */
    @GetMapping("/skillDetail/{id}")
    public String getSkillDetail(@ModelAttribute InputSkillForm form, Model model,
            @PathVariable("id") String employee_id) {

        // コンテンツ部分にスキル情報詳細を表示するための文字列を登録
        model.addAttribute("contents", "b/skillDetail :: skillDetail_contents");

        // 社員IDのチェック
        if (employee_id != null && StrUtils.getStrLength(employee_id) > 0) {

            // スキル情報を取得
            Skill skill = skillService.selectOne(employee_id);

            // スキル情報とか必要な情報をここで取得してモデルに入れる

            // Skillクラスをフォームクラスに変換
            form.setEmployee_id(skill.getEmployee_id()); // 社員ID
            form.setEmployee_cd(skill.getEmployee_cd()); // 社員CD
            form.setEmployee_name1_last(skill.getEmployee_name1_last()); // 社員名漢字（姓）
            form.setEmployee_name1_first(skill.getEmployee_name1_first()); // 社員名漢字（名）
            form.setFinal_education(skill.getFinal_education()); // 最終学歴
            form.setDepartment(skill.getDepartment());// 学科
            form.setGraduation_date(skill.getGraduation_date());// 卒業年月
            form.setNotices(skill.getNotices());// 特記事項
            form.setBiko(skill.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputSkillForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * スキル情報詳細画面のスキル情報更新用処理.<BR>
     * 
     * 画面から入力したスキル情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getSkillList(model)
     */
    @PostMapping(value = "/skillDetail", params = "update")
    public String postSkillDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputSkillForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、スキル情報詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、スキル情報詳細画面に戻ります
            return getSkillDetail(form, model, "");
        }

        // 卒業年月チェック
        if (form.getGraduation_date() != null && !form.getGraduation_date().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getGraduation_date()) == false) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "卒業年月" }));
                return getSkillDetail(form, model, "");
            }
        }

        // Skillインスタンスの生成
        Skill skill = new Skill();

        skill.setEmployee_id(form.getEmployee_id()); // 社員ID
        skill.setFinal_education(form.getFinal_education()); // 最終学歴
        skill.setDepartment(form.getDepartment()); // 学科
        skill.setGraduation_date(form.getGraduation_date()); // 卒業年月
        skill.setNotices(form.getNotices()); // 特記事項
        skill.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        skill.setInsert_user(user_auth.getUsername()); // 作成者
        skill.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = skillService.updateOne(skill);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 社員マスタ一覧画面を表示
        return getSkillList(model);
    }

    /**
     * スキル情報詳細画面の戻る処理.<BR>
     * 
     * スキル一覧画面に戻る。
     * 
     * @param model モデル
     * @return getskillList(model)
     */
    @PostMapping(value = "/skillDetail", params = "back")
    public String postSkillDetailback(Model model) {
        // スキル情報一覧画面を表示
        return getSkillList(model);
    }

}