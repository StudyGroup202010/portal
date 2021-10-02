package com.portal.a.company.controller;

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
import com.portal.a.company.domain.model.CompanyListCsvView;
import com.portal.a.company.domain.model.CompanyListXlsxView;
import com.portal.a.company.domain.model.InputCompanyForm;
import com.portal.a.company.domain.model.SelectCompanyForm;
import com.portal.a.company.domain.service.CompanyService;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 会社マスタ用のController
 * 
 */
@Controller
@Slf4j
public class companyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 会社マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 会社マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/companyList")
    public String getCompanyList(Model model) {

        // 検索条件のformを登録
        SelectCompanyForm form = new SelectCompanyForm();
        model.addAttribute("selectCompanyForm", form);

        // コンテンツ部分に会社マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/companyList :: companyList_contents");

        // 会社マスタ一覧の生成
        List<Company> companyList = companyService.selectMany();

        // Modelに会社リストを登録
        model.addAttribute("companyList", companyList);

        // データ件数を取得
        int count = companyList.size();
        model.addAttribute("companyListCount", count);

        return "z/homeLayout";
    }

    /**
     * 会社マスタ一覧画面の検索用処理.<br>
     * 
     * 画面から入力した検索条件で会社マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/companyList", params = "selectby")
    public String getCompanyListByCompanyid(@ModelAttribute SelectCompanyForm form, BindingResult bindingResult,
            Model model) {

        // コンテンツ部分に会社マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/companyList :: companyList_contents");

        // 会社マスタ情報を取得
        List<Company> companyList = companyService.selectBy(form.getCompany_name(), form.getBiko());

        // Modelに会社マスタリストを登録
        model.addAttribute("companyList", companyList);

        // データ件数を登録
        int count = companyList.size();
        model.addAttribute("companyListCount", count);

        return "z/homeLayout";
    }

    /**
     * 会社マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 会社情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/companyUpdate")
    public String getSignUp(@ModelAttribute InputCompanyForm form, Model model) {

        // コンテンツ部分に会社マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/companyUpdate :: companyUpdate_contents");

        // 会社マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 会社マスタ登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/companyUpdate")
    public String postSignUp(@ModelAttribute @Validated InputCompanyForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、会社マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、会社マスタ登録画面に戻ります
            return getSignUp(form, model);
        }

        // 会社マスタinsert用変数
        Company company = new Company();

        company.setCompany_cd(form.getCompany_cd()); // 会社CD
        company.setCompany_name(form.getCompany_name()); // 会社名
        company.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        company.setInsert_user(user_auth.getUsername()); // 作成者

        // 会社マスタ登録処理(company)
        try {
            boolean result = companyService.insertOne(company);

            // 会社マスタ登録結果の判定
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
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { company.getCompany_cd() }));
            return getSignUp(form, model);
        }

        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }

    /**
     * 会社マスタ登録画面の戻る処理.<BR>
     * 
     * 会社マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getCompanyList(model)
     */
    @PostMapping(value = "/companyUpdate", params = "back")
    public String postCompanyUpdateback(Model model) {
        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }

    /**
     * 会社マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 会社マスタ詳細画面を表示する。
     * 
     * @param form       入力用form
     * @param model      モデル
     * @param company_cd 詳細情報を表示する
     * @return z/homeLayout
     */
    @GetMapping("/companyDetail/{id}")
    public String getCompanyDetail(@ModelAttribute InputCompanyForm form, Model model,
            @PathVariable("id") String company_cd) {

        // コンテンツ部分に会社マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/companyDetail :: companyDetail_contents");

        // 会社CDのチェック
        if (company_cd != null && StrUtils.getStrLength(company_cd) > 0) {

            // 会社マスタ情報を取得
            Company company = companyService.selectOne(company_cd);

            // Companyクラスをフォームクラスに変換
            form.setCompany_cd(company.getCompany_cd()); // 会社CD
            form.setCompany_name(company.getCompany_name()); // 会社名
            form.setBiko(company.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputCompanyForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 会社マスタ詳細画面の会社マスタ更新用処理.<BR>
     * 
     * 画面から入力した会社マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getCompanyList(model)
     */
    @PostMapping(value = "/companyDetail", params = "update")
    public String postCompanyDetailUpdate(@ModelAttribute @Validated InputCompanyForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、会社マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、会社マスタ詳細画面に戻ります
            return getCompanyDetail(form, model, "");
        }

        // Companyインスタンスの生成
        Company company = new Company();

        company.setCompany_cd(form.getCompany_cd()); // 会社CD
        company.setCompany_name(form.getCompany_name()); // 会社名
        company.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        company.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = companyService.updateOne(company);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }

    /**
     * 会社マスタ詳細画面の会社マスタ削除用処理.<BR>
     * 
     * 画面から入力した会社マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getCompanyList(model)
     */
    @PostMapping(value = "/companyDetail", params = "delete")
    public String postCompanyDetailDelete(@ModelAttribute InputCompanyForm form, Model model) {

        // 削除実行
        boolean result = companyService.deleteOne(form.getCompany_cd());

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }

    /**
     * 会社マスタ詳細画面の戻る処理.<BR>
     * 
     * 会社マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getCompanyList(model)
     */
    @PostMapping(value = "/companyDetail", params = "back")
    public String postCompanyDetailback(Model model) {
        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }
    
    /**
     * 会社マスタ一覧のCSV出力用処理.<br>
     * 
     * 会社マスタ一覧のCSVファイルを出力する。
     * 
     * @param model モデル
     * @return ResponseEntity(bytes, header, HttpStatus.OK)
     */
    @GetMapping("/companyList/csv")
    public CompanyListCsvView getCompanyListCsv(CompanyListCsvView model) {

        // 会社マスタ一覧の生成
        List<Company> companyList = companyService.selectMany();

        // Modelに会社リストを登録
        model.addStaticAttribute("companyList", companyList);

        return model;
    }
    
    /**
     * 会社マスタ一覧のExcel出力用処理.<br>
     * 
     * 会社マスタ一覧のEXCELファイルを出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/companyList/excel")
    public CompanyListXlsxView excel(CompanyListXlsxView model) {

        // 会社マスタ一覧の生成
        List<Company> companyList = companyService.selectMany();

        // Modelに会社リストを登録
        model.addStaticAttribute("companyList", companyList);

        // データ件数を取得
        int count = companyList.size();
        model.addStaticAttribute("companyListCount", count);

        return model;
    }
    
    
    
}