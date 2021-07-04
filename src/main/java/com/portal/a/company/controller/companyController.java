package com.portal.a.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.a.common.domain.model.Company;
import com.portal.a.company.domain.model.InputCompanyForm;
import com.portal.a.company.domain.model.SelectCompanyForm;
import com.portal.a.company.domain.service.CompanyService;
import com.portal.z.common.domain.util.StrUtils;

/**
 * companyマスタ用のController
 * 
 */
@Controller
//@Slf4j
public class companyController {

    @Autowired
    private CompanyService companyService;

    //@Autowired
    //private MassageUtils massageUtils;

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

        // Modelにユーザーリストを登録
        model.addAttribute("companyList", companyList);

        // データ件数を取得
        int count = companyList.size();
        model.addAttribute("companyListCount", count);

        return "z/homeLayout";
    }

//    /**
//     * 環境マスタ一覧画面のユーザー検索用処理.<br>
//     * 
//     * 画面から入力した検索条件で環境マスタ情報を検索する。
//     * 
//     * @param form          検索条件のform
//     * @param bindingResult 検索条件の入力値
//     * @param model         モデル
//     * @return z/homeLayout
//     */
//    @RequestMapping(value = "/envList", params = "selectby")
//    public String getEnvListByEnvid(@ModelAttribute SelectEnvForm form, BindingResult bindingResult, Model model) {
//
//        // コンテンツ部分に環境マスタ一覧を表示するための文字列を登録
//        model.addAttribute("contents", "a/envList :: envList_contents");
//
//        // 環境マスタ情報を取得
//        List<Env> envList = envService.selectBy(form.getEnv_id(), form.getEnv_kbn(), form.getEnv_txt(), form.getBiko());
//
//        // Modelに環境マスタリストを登録
//        model.addAttribute("envList", envList);
//
//        // データ件数を登録
//        int count = envList.size();
//        model.addAttribute("envListCount", count);
//
//        return "z/homeLayout";
//    }
//
//    /**
//     * 環境マスタ登録画面のGETメソッド用処理.<BR>
//     * 
//     * 環境情報の新規登録画面を表示する。
//     * 
//     * @param form  入力用のform
//     * @param model モデル
//     * @return z/homeLayout
//     */
    @GetMapping("/companyUpdate")
    public String getSignUp(@ModelAttribute InputCompanyForm form, Model model) {

        // コンテンツ部分に会社マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/companyUpdate :: companyUpdate_contents");

        // 会社マスタ登録画面に画面遷移
        return "z/homeLayout";
    }
//
//    /**
//     * 環境マスタ登録画面のPOSTメソッド用処理.
//     * 
//     * @param form          フォーム
//     * @param bindingResult 処理結果
//     * @param model         モデル
//     * @return 遷移先の情報(String)
//     */
//    @PostMapping("/envUpdate")
//    public String postSignUp(@ModelAttribute @Validated InputEnvForm form, BindingResult bindingResult, Model model) {
//
//        // 入力チェックに引っかかった場合、環境マスタ登録画面に戻る
//        if (bindingResult.hasErrors()) {
//            // GETリクエスト用のメソッドを呼び出して、環境マスタ登録画面に戻ります
//            return getSignUp(form, model);
//        }
//
//        // 環境マスタinsert用変数
//        Env env = new Env();
//
//        env.setEnv_id(form.getEnv_id()); // 環境ID
//        env.setEnv_kbn(form.getEnv_kbn()); // 環境区分
//        env.setEnv_num(form.getEnv_num()); // 環境連番
//        env.setEnv_txt(form.getEnv_txt()); // 環境値
//        env.setBiko(form.getBiko()); // 備考
//
//        // ログインユーザー情報の取得
//        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//        env.setInsert_user(user_auth.getUsername()); // 作成者
//
//        // 環境マスタ登録処理(env)
//        try {
//            boolean result = envService.insertOne(env);
//
//            // 環境マスタ登録結果の判定
//            if (result == true) {
//                model.addAttribute("result", "登録成功");
//                log.info("登録成功");
//            } else {
//                model.addAttribute("result", "登録失敗");
//                log.error("登録失敗");
//            }
//        } catch (DuplicateKeyException e) {
//            // 一意制約エラーが発生したとき。
//            String messageKey = "e.co.fw.2.003";
//            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { env.getEnv_id() }));
//            return getSignUp(form, model);
//        }
//
//        // 環境マスタ一覧画面を表示
//        return getEnvList(model);
//    }
//
//    /**
//     * 環境マスタ登録画面の戻る処理.<BR>
//     * 
//     * 環境マスタ一覧画面に戻る。
//     * 
//     * @param model モデル
//     * @return getEnvList(model)
//     */
    @PostMapping(value = "/companyUpdate", params = "back")
    public String postCompanyUpdateback(Model model) {
        // 会社マスタ一覧画面を表示
        return getCompanyList(model);
    }
//
//    /**
//     * 環境マスタ詳細画面のGETメソッド用処理.<BR>
//     * 
//     * 環境マスタ詳細画面を表示する。
//     * 
//     * @param form   入力用form
//     * @param model  モデル
//     * @param env_id 詳細情報を表示するenv_id
//     * @return z/homeLayout
//     */
    @GetMapping("/companyDetail/{id}")
    public String getCompanyDetail(@ModelAttribute InputCompanyForm form, Model model, @PathVariable("id") String company_cd) {

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
            model.addAttribute("inputEnvForm", form);
        }

        return "z/homeLayout";
    }
//
//    /**
//     * 環境マスタ詳細画面の環境マスタ更新用処理.<BR>
//     * 
//     * 画面から入力した環境マスタ情報でデータを更新する。
//     * 
//     * @param form          入力用form
//     * @param bindingResult 更新する情報
//     * @param model         モデル
//     * @return getEnvList(model)
//     */
//    @PostMapping(value = "/envDetail", params = "update")
//    public String postEnvDetailUpdate(@ModelAttribute @Validated InputEnvForm form, BindingResult bindingResult,
//            Model model) {
//
//        // 入力チェックに引っかかった場合、環境マスタ詳細画面に戻る
//        if (bindingResult.hasErrors()) {
//            // GETリクエスト用のメソッドを呼び出して、環境マスタ詳細画面に戻ります
//            return getEnvDetail(form, model, "");
//        }
//
//        // Envインスタンスの生成
//        Env env = new Env();
//
//        env.setEnv_id(form.getEnv_id()); // 環境ID
//        env.setEnv_kbn(form.getEnv_kbn()); // 環境区分
//        env.setEnv_num(form.getEnv_num()); // 環境連番
//        env.setEnv_txt(form.getEnv_txt()); // 環境値
//        env.setBiko(form.getBiko()); // 備考
//
//        // ログインユーザー情報の取得
//        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//        env.setUpdate_user(user_auth.getUsername()); // 更新者
//
//        // 更新実行
//        boolean result = envService.updateOne(env);
//
//        if (result == true) {
//            model.addAttribute("result", "更新成功");
//            log.info("更新成功");
//        } else {
//            model.addAttribute("result", "更新失敗");
//            log.error("更新失敗");
//        }
//
//        // 環境マスタ一覧画面を表示
//        return getEnvList(model);
//    }
//
//    /**
//     * 環境マスタ詳細画面の環境マスタ削除用処理.<BR>
//     * 
//     * 画面から入力した環境マスタ情報でデータを削除する。
//     * 
//     * @param form  入力用form
//     * @param model モデル
//     * @return getEnvList(model)
//     */
//    @PostMapping(value = "/envDetail", params = "delete")
//    public String postEnvDetailDelete(@ModelAttribute InputEnvForm form, Model model) {
//
//        // 削除実行
//        boolean result = envService.deleteOne(form.getEnv_id());
//
//        if (result == true) {
//            model.addAttribute("result", "削除成功");
//            log.info("削除成功");
//        } else {
//            model.addAttribute("result", "削除失敗");
//            log.error("削除失敗");
//        }
//        // 環境マスタ一覧画面を表示
//        return getEnvList(model);
//    }

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
}