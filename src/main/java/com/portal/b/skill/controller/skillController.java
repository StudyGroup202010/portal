package com.portal.b.skill.controller;

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

import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Careertechnology;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.common.domain.model.Technology;
import com.portal.b.skill.domain.model.CreateOrder;
import com.portal.b.skill.domain.model.InputCareerForm;
import com.portal.b.skill.domain.model.InputSkillForm;
import com.portal.b.skill.domain.model.SelectCareerForm;
import com.portal.b.skill.domain.model.SelectSkillForm;
import com.portal.b.skill.domain.model.UpdateOrder;
import com.portal.b.skill.domain.service.SkillService;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;

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
        List<Skill> skillList = skillService.selectSkillMany();

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
        List<Skill> skillList = skillService.selectSkillBy(form.getEmployee_cd(), form.getEmployee_name1_last(),
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
     * @param Skillform   入力用form
     * @param model       モデル
     * @param employee_id 詳細情報を表示するemployee_id
     * @return z/homeLayout
     */
    @GetMapping("/skillDetail/{id}")
    public String getSkillDetail(@ModelAttribute InputSkillForm Skillform, Model model,
            @PathVariable("id") String employee_id) {

        // コンテンツ部分にスキル情報詳細を表示するための文字列を登録
        model.addAttribute("contents", "b/skillDetail :: skillDetail_contents");

        // 社員IDのチェック
        if (employee_id != null && !employee_id.isEmpty()) {

            // スキル情報を取得
            Skill skill = skillService.selectSkillOne(employee_id);

            // スキル情報とか必要な情報をここで取得してモデルに入れる

            // Skillクラスをフォームクラスに変換
            Skillform.setEmployee_id(skill.getEmployee_id()); // 社員ID
            Skillform.setEmployee_cd(skill.getEmployee_cd()); // 社員CD
            Skillform.setEmployee_name1_last(skill.getEmployee_name1_last()); // 社員名漢字（姓）
            Skillform.setEmployee_name1_first(skill.getEmployee_name1_first()); // 社員名漢字（名）
            Skillform.setFinal_education(skill.getFinal_education()); // 最終学歴
            Skillform.setDepartment(skill.getDepartment());// 学科
            Skillform.setGraduation_date(skill.getGraduation_date());// 卒業年月
            Skillform.setNotices(skill.getNotices());// 特記事項
            Skillform.setBiko(skill.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputSkillForm", Skillform);
        }

        return "z/homeLayout";
    }

    /**
     * スキル情報詳細画面のスキル情報更新用処理.<BR>
     * 
     * 画面から入力したスキル情報でデータを更新する。
     * 
     * @param skillform     入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getSkillList(model)
     */
    @PostMapping(value = "/skillDetail", params = "update")
    public String postSkillDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputSkillForm skillform,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、スキル情報詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、スキル情報詳細画面に戻ります
            return getSkillDetail(skillform, model, "");
        }

        // 卒業年月チェック
        if (skillform.getGraduation_date() != null && !skillform.getGraduation_date().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(skillform.getGraduation_date()) == false) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "卒業年月" }));
                return getSkillDetail(skillform, model, "");
            }
        }

        // Skillインスタンスの生成
        Skill skill = new Skill();

        skill.setEmployee_id(skillform.getEmployee_id()); // 社員ID
        skill.setFinal_education(skillform.getFinal_education()); // 最終学歴
        skill.setDepartment(skillform.getDepartment()); // 学科
        skill.setGraduation_date(skillform.getGraduation_date()); // 卒業年月
        skill.setNotices(skillform.getNotices()); // 特記事項
        skill.setBiko(skillform.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        skill.setInsert_user(user_auth.getUsername()); // 作成者
        skill.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = skillService.updateSkillOne(skill);

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

    /**
     * 業務経歴一覧画面のGETメソッド用処理.<BR>
     * 
     * 業務経歴一覧画面を表示する。
     * 
     * @param model       モデル
     * @param employee_id 詳細情報を表示するemployee_id
     * @return z/homeLayout
     */
    @GetMapping("/careerList/{id}")
    public String getCareerList(Model model, @PathVariable("id") String employee_id) {

        // コンテンツ部分に業務経歴一覧を表示するための文字列を登録
        model.addAttribute("contents", "b/careerList :: careerList_contents");

        // 社員IDのチェック
        if (employee_id != null && !employee_id.isEmpty()) {

            // 検索条件に値をセット
            SelectCareerForm form = new SelectCareerForm();
            form.setEmployee_id(employee_id);
            model.addAttribute("selectCareerForm", form);

            // 選択したユーザ情報をセット
            Skill skill = skillService.selectSkillOne(employee_id);
            model.addAttribute("selected_employee_cd", skill.getEmployee_cd());
            model.addAttribute("selected_employee_name1_last", skill.getEmployee_name1_last());
            model.addAttribute("selected_employee_name1_first", skill.getEmployee_name1_first());

            // 業務経歴情報を取得
            List<Career> careerList = skillService.selectCareerBy2(employee_id);

            // Modelにスキルリストを登録
            model.addAttribute("careerList", careerList);

            // データ件数を登録
            int count = careerList.size();
            model.addAttribute("careerListCount", count);
        }

        return "z/homeLayout";
    }

    /**
     * 業務経歴一覧画面の業務経歴検索用処理.<br>
     * 
     * 画面から入力した検索条件で業務経歴情報を検索する。
     * 
     * @param Careerform    検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/careerList", params = "selectby")
    public String getCareerListByEmployeecd(@ModelAttribute SelectCareerForm Careerform, BindingResult bindingResult,
            Model model) {

        // コンテンツ部分に業務経歴一覧画面を表示するための文字列を登録
        model.addAttribute("contents", "b/careerList :: careerList_contents");

        // 選択したユーザ情報をセット
        Skill skill = skillService.selectSkillOne(Careerform.getEmployee_id());
        model.addAttribute("selected_employee_cd", skill.getEmployee_cd());
        model.addAttribute("selected_employee_name1_last", skill.getEmployee_name1_last());
        model.addAttribute("selected_employee_name1_first", skill.getEmployee_name1_first());

        // 業務経歴情報を取得
        List<Career> careerList = skillService.selectCareerBy1(Careerform.getEmployee_id(),
                Careerform.getBusiness_content(), Careerform.getBiko());

        // Modelにスキルリストを登録
        model.addAttribute("careerList", careerList);

        // データ件数を登録
        int count = careerList.size();
        model.addAttribute("careerListCount", count);

        return "z/homeLayout";
    }

    /**
     * 業務経歴一覧画面の戻る処理.<BR>
     * 
     * スキル一覧画面に戻る。
     * 
     * @param model モデル
     * @return getskillList(model)
     */
    @PostMapping(value = "/careerList", params = "back")
    public String postCareerListback(Model model) {
        // スキル情報一覧画面を表示
        return getSkillList(model);
    }

    /**
     * 業務経歴登録画面のGETメソッド用処理.<BR>
     * 
     * 業務経歴情報の新規登録画面を表示する。
     * 
     * @param form        入力用のform
     * @param model       モデル
     * @param employee_id employee_id
     * @return z/homeLayout
     */
    @GetMapping("/careerUpdate/{id}")
    public String getCareerUpdate(@ModelAttribute InputCareerForm form, Model model,
            @PathVariable("id") String employee_id) {

        // コンテンツ部分に業務経歴登録を表示するための文字列を登録
        model.addAttribute("contents", "b/careerUpdate :: careerUpdate_contents");

        // 社員IDのチェック
        if (employee_id != null && !employee_id.isEmpty()) {

            // スキル情報を取得
            Skill skill = skillService.selectSkillOne(employee_id);

            // Skillクラスをフォームクラスに変換
            form.setEmployee_id(employee_id); // 社員ID
            form.setEmployee_cd(skill.getEmployee_cd()); // 社員CD
            form.setEmployee_name1_last(skill.getEmployee_name1_last()); // 社員名漢字（姓）
            form.setEmployee_name1_first(skill.getEmployee_name1_first()); // 社員名漢字（名）

            // Modelに登録
            model.addAttribute("InputCareerForm", form);

            // 技術マスタ情報を取得（開発言語）
            List<Technology> technologyList = skillService.selectTechnologyBy(Constants.TECHNOLOGY_KBN_OS);
            // Modelに登録
            model.addAttribute("technologyList", technologyList);

        }
        // 業務経歴画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 業務経歴登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/careerUpdate")
    public String postCareerUpdate(@ModelAttribute @Validated(CreateOrder.class) InputCareerForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、業務経歴登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻ります
            return getCareerUpdate(form, model, form.getEmployee_id());
        }

        // 開始年月チェック
        if (form.getStart_yearmonth() != null && !form.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getCareerUpdate(form, model, form.getEmployee_id());
            }
        }

        // 終了年月チェック
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "終了年月" }));
                return getCareerUpdate(form, model, form.getEmployee_id());
            }
        }

        // 年月チェック(開始年月 <= 終了年月）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(DateUtils.getDateFromStringmonth(form.getStart_yearmonth()).atStartOfDay(),
                    DateUtils.getDateFromStringmonth(form.getEnd_yearmonth()).atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻りますに戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "開始年月：" + form.getStart_yearmonth(), "終了年月：" + form.getEnd_yearmonth() }));
                return getCareerUpdate(form, model, form.getEmployee_id());
            }
        }

        // 業務経歴insert用変数
        Career career = new Career();

        career.setEmployee_id(form.getEmployee_id()); // 社員ID
        String next_certification_no = skillService.selectCareerBy3().getCertification_no();// 経歴番号
        career.setCertification_no(next_certification_no);
        career.setDisp_order(form.getDisp_order()); // 表示順
        career.setStart_yearmonth(form.getStart_yearmonth()); // 開始年月
        career.setEnd_yearmonth(form.getEnd_yearmonth()); // 終了年月
        career.setBusiness_content(form.getBusiness_content()); // 業務内容
        career.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        career.setInsert_user(user_auth.getUsername()); // 作成者

        try {
            // 業務経歴登録処理(career)
            boolean result1 = skillService.insertCareerOne(career);

            // 業務経歴登録結果の判定
            if (result1 == true) {

                // 機種／OSを選択した場合
                if (form.getTechnology_id() != null) {
                    if (0 < form.getTechnology_id().length) {

                        // 機種／OS用変数を定義
                        String[] getTechnology_id = form.getTechnology_id();

                        // 業務経歴技術insert用変数
                        Careertechnology careertechnology = new Careertechnology();

                        for (int i = 0; i < getTechnology_id.length; i++) {
                            careertechnology.setEmployee_id(form.getEmployee_id());// 社員ID
                            careertechnology.setCertification_no(next_certification_no);// 経歴番号
                            careertechnology.setTechnology_id(getTechnology_id[i]); // 技術ID
                            careertechnology.setBiko(form.getBiko());// 備考
                            careertechnology.setInsert_user(user_auth.getUsername()); // 作成者

                            boolean result2 = skillService.insertCareertechnologyOne(careertechnology);

                            if (result2 == true) {
                                model.addAttribute("result", "登録成功");
                                log.info("登録成功");
                            } else {
                                model.addAttribute("result", "登録失敗");
                                log.error("登録失敗");
                            }
                        }
                    }
                } else {
                    model.addAttribute("result", "登録成功");
                    log.info("登録成功");
                }
            } else {
                model.addAttribute("result", "登録失敗");
                log.error("登録失敗");
            }

        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生したとき。
            String message = "経歴番号 " + String.valueOf(career.getCertification_no());
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getCareerUpdate(form, model, form.getEmployee_id());
        }

        // 業務経歴一覧画面を表示
        return getCareerList(model, form.getEmployee_id());
    }

    /**
     * 業務経歴登録画面の戻る処理.<BR>
     * 
     * 業務経歴一覧画面に戻る。
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return getCareerList(model)
     */
    @PostMapping(value = "/careerUpdate", params = "back")
    public String postCareerUpdateback(@ModelAttribute @Validated InputCareerForm form, BindingResult bindingResult,
            Model model) {
        // 業務経歴一覧画面を表示
        return getCareerList(model, form.getEmployee_id());
    }

    /**
     * 業務経歴詳細画面のGETメソッド用処理.<BR>
     * 
     * 業務経歴詳細画面を表示する。
     * 
     * @param Careerform       入力用form
     * @param model            モデル
     * @param employee_id      詳細情報を表示するemployee_id
     * @param certification_no 詳細情報を表示するcertification_no
     * @return z/homeLayout
     */
    @GetMapping("/careerDetail/{id}/{no}")
    public String getCareerDetail(@ModelAttribute InputCareerForm Careerform, Model model,
            @PathVariable("id") String employee_id, @PathVariable("no") String certification_no) {

        // コンテンツ部分にスキル情報詳細を表示するための文字列を登録
        model.addAttribute("contents", "b/careerDetail :: careerDetail_contents");

        // 社員IDのチェック
        if ((employee_id != null) && (!employee_id.isEmpty()) && (certification_no != null)
                && (!certification_no.isEmpty())) {

            // スキル情報を取得
            Skill skill = skillService.selectSkillOne(employee_id);

            // Skillクラスをフォームクラスに変換
            Careerform.setEmployee_id(employee_id); // 社員ID
            Careerform.setEmployee_cd(skill.getEmployee_cd()); // 社員CD
            Careerform.setEmployee_name1_last(skill.getEmployee_name1_last()); // 社員名漢字（姓）
            Careerform.setEmployee_name1_first(skill.getEmployee_name1_first()); // 社員名漢字（名）

            // 業務経歴を取得
            Career career = skillService.selectCareerOne(employee_id, certification_no);

            // Careerクラスをフォームクラスに変換
            Careerform.setCertification_no(certification_no); // 経歴番号
            Careerform.setDisp_order(career.getDisp_order()); // 表示順
            Careerform.setStart_yearmonth(career.getStart_yearmonth());// 開始年月
            Careerform.setEnd_yearmonth(career.getEnd_yearmonth());// 終了年月
            Careerform.setBusiness_content(career.getBusiness_content());// 業務内容
            Careerform.setBiko(career.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputCareerForm", Careerform);

            // 技術マスタ情報を取得（開発言語）
            List<Careertechnology> careertechnologyList = skillService.selectCareertechnologyBy(employee_id,
                    certification_no, Constants.TECHNOLOGY_KBN_OS);
            // Modelに登録
            model.addAttribute("careertechnologyList", careertechnologyList);
        }

        return "z/homeLayout";
    }

    /**
     * 業務経歴詳細画面の業務経歴更新用処理.<BR>
     * 
     * 画面から入力した業務経歴でデータを更新する。
     * 
     * @param careerform    入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getCareerList(model)
     */
    @PostMapping(value = "/careerDetail", params = "update")
    public String postCareerDetaiUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputCareerForm careerform,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、業務経歴詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、業務経歴詳細画面に戻ります
            return getCareerDetail(careerform, model, "", "");
        }

        // 開始年月チェック
        if (careerform.getStart_yearmonth() != null && !careerform.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(careerform.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getCareerDetail(careerform, model, "", "");
            }
        }

        // 終了年月チェック
        if (careerform.getEnd_yearmonth() != null && !careerform.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(careerform.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "終了年月" }));
                return getCareerDetail(careerform, model, "", "");
            }
        }

        // 年月チェック(開始年月 <= 終了年月）
        if (careerform.getEnd_yearmonth() != null && !careerform.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(
                    DateUtils.getDateFromStringmonth(careerform.getStart_yearmonth()).atStartOfDay(),
                    DateUtils.getDateFromStringmonth(careerform.getEnd_yearmonth()).atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、業務経歴登録画面に戻りますに戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022", new String[] {
                        "開始年月：" + careerform.getStart_yearmonth(), "終了年月：" + careerform.getEnd_yearmonth() }));
                return getCareerDetail(careerform, model, "", "");
            }
        }

        // 業務経歴インスタンスの生成
        Career career = new Career();

        // Careerクラスをフォームクラスに変換
        career.setEmployee_id(careerform.getEmployee_id()); // 社員ID
        career.setCertification_no(careerform.getCertification_no()); // 経歴番号
        career.setDisp_order(careerform.getDisp_order()); // 表示順
        career.setStart_yearmonth(careerform.getStart_yearmonth());// 開始年月
        career.setEnd_yearmonth(careerform.getEnd_yearmonth());// 終了年月
        career.setBusiness_content(careerform.getBusiness_content());// 業務内容
        career.setBiko(careerform.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        career.setInsert_user(user_auth.getUsername()); // 作成者
        career.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = skillService.updateCareerOne(career);

        if (result == true) {

            // 業務経歴技術削除実行
            // こちらは空振りする場合もあるので結果の評価はしない。
            skillService.deleteCareertechnologyOne(careerform.getEmployee_id(), careerform.getCertification_no());

            // 機種／OSを選択した場合
            if ((careerform.getTechnology_id() != null) && (0 < careerform.getTechnology_id().length)) {

                // 機種／OS用変数を定義
                String[] getCareertechnology_id = careerform.getTechnology_id();

                // 業務経歴技術insert用変数
                Careertechnology careertechnology = new Careertechnology();

                for (int i = 0; i < getCareertechnology_id.length; i++) {
                    careertechnology.setEmployee_id(careerform.getEmployee_id());// 社員ID
                    careertechnology.setCertification_no(careerform.getCertification_no());// 経歴番号
                    careertechnology.setTechnology_id(getCareertechnology_id[i]); // 技術ID
                    careertechnology.setBiko(careerform.getBiko());// 備考
                    careertechnology.setInsert_user(user_auth.getUsername()); // 作成者

                    // insert文を繰り替えしている。本来はまとめておいて一括してinsertしたい
                    boolean result2 = skillService.insertCareertechnologyOne(careertechnology);

                    if (result2 == true) {
                        model.addAttribute("result", "更新成功");
                        log.info("更新成功");
                    } else {
                        model.addAttribute("result", "更新失敗");
                        log.error("更新失敗");
                    }
                }
            } else {
                model.addAttribute("result", "更新成功");
                log.info("更新成功");
            }
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 業務経歴一覧画面を表示
        return getCareerList(model, careerform.getEmployee_id());
    }

    /**
     * 業務経歴詳細画面の業務経歴削除用処理.<BR>
     * 
     * 画面から入力した業務経歴情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getCareerList(model)
     */
    @PostMapping(value = "/careerDetail", params = "delete")
    public String postCareerDetaiDelete(@ModelAttribute InputCareerForm form, Model model) {

        // 業務経歴技術削除実行
        // こちらは空振りする場合もあるので結果の評価はしない。
        skillService.deleteCareertechnologyOne(form.getEmployee_id(), form.getCertification_no());
        // 業務経歴削除実行
        boolean result2 = skillService.deleteCareerOne(form.getEmployee_id(), form.getCertification_no());

        if (result2 == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 業務経歴一覧画面を表示
        return getCareerList(model, form.getEmployee_id());
    }

    /**
     * 業務経歴詳細画面の戻る処理.<BR>
     * 
     * 業務経歴一覧画面に戻る。
     * 
     * @param form          入力用form
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return getcareerList(model)
     */
    @PostMapping(value = "/careerDetail", params = "back")
    public String postCareerDetailback(@ModelAttribute @Validated InputCareerForm form, BindingResult bindingResult,
            Model model) {
        // 業務経歴一覧画面を表示
        return getCareerList(model, form.getEmployee_id());
    }

}