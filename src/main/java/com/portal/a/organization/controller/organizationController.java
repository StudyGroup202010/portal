package com.portal.a.organization.controller;

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

import com.portal.a.common.domain.model.Company;
import com.portal.a.common.domain.model.Organization;
import com.portal.a.organization.domain.model.CreateOrder;
import com.portal.a.organization.domain.model.InputOrganizationForm;
import com.portal.a.organization.domain.model.OrganizationListXlsxView;
import com.portal.a.organization.domain.model.SelectOrganizationForm;
import com.portal.a.organization.domain.model.UpdateOrder;
import com.portal.a.organization.domain.service.OrganizationService;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 組織マスタ用のController
 * 
 */
@Controller
@Slf4j
public class organizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 組織マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 組織マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/organizationList")
    public String getOrganizationList(Model model) {

        // 検索条件のformを登録
        SelectOrganizationForm form = new SelectOrganizationForm();
        model.addAttribute("selectOrganizationForm", form);

        // コンテンツ部分に組織マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/organizationList :: organizationList_contents");

        // 組織マスタ一覧の生成
        List<Organization> organizationList = organizationService.selectMany();

        // Modelに組織リストを登録
        model.addAttribute("organizationList", organizationList);

        // データ件数を取得
        int count = organizationList.size();
        model.addAttribute("organizationListCount", count);

        return "z/homeLayout";
    }

    /**
     * 組織マスタ一覧画面の検索用処理.<br>
     * 
     * 画面から入力した検索条件で組織マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/organizationList", params = "selectby")
    public String getOrganizationListByOrganizationid(@ModelAttribute SelectOrganizationForm form,
            BindingResult bindingResult, Model model) {

        // コンテンツ部分に組織マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/organizationList :: organizationList_contents");

        // 組織マスタ情報を取得
        List<Organization> organizationList = organizationService.selectBy(form.getOrganization_name(),
                form.getCompany_cd(), form.getStart_yearmonth(), form.getEnd_yearmonth(), form.getBiko());

        // Modelに組織マスタリストを登録
        model.addAttribute("organizationList", organizationList);

        // データ件数を登録
        int count = organizationList.size();
        model.addAttribute("organizationListCount", count);

        return "z/homeLayout";
    }

    /**
     * 組織マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 組織情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/organizationUpdate")
    public String getSignUp(@ModelAttribute InputOrganizationForm form, Model model) {

        // コンテンツ部分に組織マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/organizationUpdate :: organizationUpdate_contents");

        // プルダウンの内容を設定
        // 会社マスタ一覧を取得
        List<Company> companyList = organizationService.selectManyCompany();

        // Modelに会社マスタのリストを登録
        model.addAttribute("companyList", companyList);

        // 組織マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 組織マスタ登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/organizationUpdate")
    public String postSignUp(@ModelAttribute @Validated(CreateOrder.class) InputOrganizationForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、組織マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
            return getSignUp(form, model);
        }

        // 年月チェック（start_yearmonth）
        if (form.getStart_yearmonth() != null && !form.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getSignUp(form, model);
            }
        }

        // 年月チェック（end_yearmonth）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "最終年月" }));
                return getSignUp(form, model);
            }
        }

        // 年月チェック(開始年月 <= 最終年月）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(DateUtils.getDateFromString(form.getStart_yearmonth() + "01").atStartOfDay(),
                    DateUtils.getDateFromString(form.getEnd_yearmonth() + "01").atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "開始年月：" + form.getStart_yearmonth(), "最終年月：" + form.getEnd_yearmonth() }));
                return getSignUp(form, model);
            }
        }

        // 組織マスタinsert用変数
        Organization organization = new Organization();

        organization.setOrganization_cd(form.getOrganization_cd()); // 組織CD
        organization.setOrganization_name(form.getOrganization_name()); // 組織名
        organization.setCompany_cd(form.getCompany_cd()); // 会社CD
        organization.setStart_yearmonth(form.getStart_yearmonth()); // 開始年月
        organization.setEnd_yearmonth(form.getEnd_yearmonth()); // 最終年月
        organization.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        organization.setInsert_user(user_auth.getUsername()); // 作成者

        // 組織マスタ登録処理(organization)
        try {
            boolean result = organizationService.insertOne(organization);

            // 組織マスタ登録結果の判定
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
            model.addAttribute("result",
                    massageUtils.getMsg(messageKey, new String[] { organization.getOrganization_cd() }));
            return getSignUp(form, model);
        }

        // 組織マスタ一覧画面を表示
        return getOrganizationList(model);
    }

    /**
     * 組織マスタ登録画面の戻る処理.<BR>
     * 
     * 組織マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getOrganizationList(model)
     */
    @PostMapping(value = "/organizationUpdate", params = "back")
    public String postOrganizationUpdateback(Model model) {
        // 組織マスタ一覧画面を表示
        return getOrganizationList(model);
    }

    /**
     * 組織マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 組織マスタ詳細画面を表示する。
     * 
     * @param form            入力用form
     * @param model           モデル
     * @param organization_cd 詳細情報を表示する
     * @return z/homeLayout
     */
    @GetMapping("/organizationDetail/{id}")
    public String getOrganizationDetail(@ModelAttribute InputOrganizationForm form, Model model,
            @PathVariable("id") String organization_cd) {

        // コンテンツ部分に組織マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/organizationDetail :: organizationDetail_contents");

        // プルダウンの内容を設定
        // 会社マスタ一覧を取得
        List<Company> companyList = organizationService.selectManyCompany();

        // Modelに会社マスタのリストを登録
        model.addAttribute("companyList", companyList);

        // 組織CDのチェック
        if (organization_cd != null && StrUtils.getStrLength(organization_cd) > 0) {

            // 組織マスタ情報を取得
            Organization organization = organizationService.selectOne(organization_cd);

            // Organizationクラスをフォームクラスに変換
            form.setOrganization_cd(organization.getOrganization_cd()); // 組織CD
            form.setOrganization_name(organization.getOrganization_name()); // 組織名
            form.setCompany_cd(organization.getCompany_cd()); // 会社CD
            form.setStart_yearmonth(organization.getStart_yearmonth()); // 開始年月
            form.setEnd_yearmonth(organization.getEnd_yearmonth()); // 最終年月
            form.setBiko(organization.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputOrganizationForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 組織マスタ詳細画面の組織マスタ更新用処理.<BR>
     * 
     * 画面から入力した組織マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getOrganizationList(model)
     */
    @PostMapping(value = "/organizationDetail", params = "update")
    public String postOrganizationDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputOrganizationForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、組織マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、組織マスタ詳細画面に戻ります
            return getOrganizationDetail(form, model, "");
        }

        // 年月チェック（start_yearmonth）
        if (form.getStart_yearmonth() != null && !form.getStart_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getStart_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "開始年月" }));
                return getOrganizationDetail(form, model, "");
            }
        }

        // 年月チェック（end_yearmonth）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.chkYearMonthFromString(form.getEnd_yearmonth()) == false) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.024", new String[] { "最終年月" }));
                return getOrganizationDetail(form, model, "");
            }
        }

        // 年月チェック(開始年月 <= 最終年月）
        if (form.getEnd_yearmonth() != null && !form.getEnd_yearmonth().isEmpty()) {
            if (DateUtils.compareDateTime(DateUtils.getDateFromString(form.getStart_yearmonth() + "01").atStartOfDay(),
                    DateUtils.getDateFromString(form.getEnd_yearmonth() + "01").atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、組織マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "開始年月：" + form.getStart_yearmonth(), "最終年月：" + form.getEnd_yearmonth() }));
                return getOrganizationDetail(form, model, "");
            }
        }

        // Organizationインスタンスの生成
        Organization organization = new Organization();

        organization.setOrganization_cd(form.getOrganization_cd()); // 組織CD
        organization.setOrganization_name(form.getOrganization_name()); // 組織名
        organization.setCompany_cd(form.getCompany_cd()); // 会社CD
        organization.setStart_yearmonth(form.getStart_yearmonth()); // 開始年月
        organization.setEnd_yearmonth(form.getEnd_yearmonth()); // 最終年月
        organization.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        organization.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = organizationService.updateOne(organization);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 組織マスタ一覧画面を表示
        return getOrganizationList(model);
    }

    /**
     * 組織マスタ詳細画面の組織マスタ削除用処理.<BR>
     * 
     * 画面から入力した組織マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getOrganizationList(model)
     */
    @PostMapping(value = "/organizationDetail", params = "delete")
    public String postOrganizationDetailDelete(@ModelAttribute InputOrganizationForm form, Model model) {

        // 削除実行
        boolean result = organizationService.deleteOne(form.getOrganization_cd());

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 組織マスタ一覧画面を表示
        return getOrganizationList(model);
    }

    /**
     * 組織マスタ詳細画面の戻る処理.<BR>
     * 
     * 組織マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getOrganizationList(model)
     */
    @PostMapping(value = "/organizationDetail", params = "back")
    public String postOrganizationDetailback(Model model) {
        // 組織マスタ一覧画面を表示
        return getOrganizationList(model);
    }

    /**
     * 組織マスタ一覧のExcel出力用処理.<br>
     * 
     * 組織マスタ一覧のEXCELファイルを出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/organizationList/excel")
    public OrganizationListXlsxView excel(OrganizationListXlsxView model) {

        // 組織マスタ一覧の生成
        List<Organization> organizationList = organizationService.selectMany();

        // Modelに組織リストを登録
        model.addStaticAttribute("organizationList", organizationList);

        // データ件数を取得
        int count = organizationList.size();
        model.addStaticAttribute("organizationListCount", count);

        return model;
    }

}