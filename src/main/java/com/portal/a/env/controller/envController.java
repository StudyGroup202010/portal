package com.portal.a.env.controller;

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

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;
import com.portal.a.common.domain.model.Env;
import com.portal.a.env.domain.model.InputEnvForm;
import com.portal.a.env.domain.model.SelectEnvForm;
import com.portal.a.env.domain.service.EnvService;

import lombok.extern.slf4j.Slf4j;

/**
 * envマスタ用のController
 * 
 */
@Controller
@Slf4j
public class envController {

    @Autowired
    private EnvService envService;

    @Autowired
    private MassageUtils massageUtils;

    /**
     * 環境マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 環境マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/envList")
    public String getEnvList(Model model) {

        // 検索条件のformを登録
        SelectEnvForm form = new SelectEnvForm();
        model.addAttribute("selectEnvForm", form);

        // コンテンツ部分に環境マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/envList :: envList_contents");

        // 環境マスタ一覧の生成
        List<Env> envList = envService.selectMany();

        // Modelにユーザーリストを登録
        model.addAttribute("envList", envList);

        // データ件数を取得
        int count = envList.size();
        model.addAttribute("envListCount", count);

        return "z/homeLayout";
    }

    /**
     * 環境マスタ一覧画面のユーザー検索用処理.<br>
     * 
     * 画面から入力した検索条件で環境マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/envList", params = "selectby")
    public String getEnvListByEnvid(@ModelAttribute SelectEnvForm form, BindingResult bindingResult, Model model) {

        // コンテンツ部分に環境マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/envList :: envList_contents");

        // 環境マスタ情報を取得
        List<Env> envList = envService.selectBy(form.getEnv_id(), form.getEnv_kbn(), form.getEnv_txt(), form.getBiko());

        // Modelに環境マスタリストを登録
        model.addAttribute("envList", envList);

        // データ件数を登録
        int count = envList.size();
        model.addAttribute("envListCount", count);

        return "z/homeLayout";
    }

    /**
     * 環境マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 環境情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/envUpdate")
    public String getSignUp(@ModelAttribute InputEnvForm form, Model model) {

        // コンテンツ部分に環境マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/envUpdate :: envUpdate_contents");

        // 環境マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 環境マスタ登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/envUpdate")
    public String postSignUp(@ModelAttribute @Validated InputEnvForm form, BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、環境マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、環境マスタ登録画面に戻ります
            return getSignUp(form, model);
        }

        // 環境マスタinsert用変数
        Env env = new Env();

        env.setEnv_id(form.getEnv_id()); // 環境ID
        env.setEnv_kbn(form.getEnv_kbn()); // 環境区分
        env.setEnv_num(form.getEnv_num()); // 環境連番
        env.setEnv_txt(form.getEnv_txt()); // 環境値
        env.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        env.setInsert_user(user_auth.getUsername()); // 作成者

        // 環境マスタ登録処理(env)
        try {
            boolean result = envService.insertOne(env);

            // 環境マスタ登録結果の判定
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
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { env.getEnv_id() }));
            return getSignUp(form, model);
        }

        // 環境マスタ一覧画面を表示
        return getEnvList(model);
    }

    /**
     * 環境マスタ登録画面の戻る処理.<BR>
     * 
     * 環境マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getEnvList(model)
     */
    @PostMapping(value = "/envUpdate", params = "back")
    public String postEnvUpdateback(Model model) {
        // 環境マスタ一覧画面を表示
        return getEnvList(model);
    }

    /**
     * 環境マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 環境マスタ詳細画面を表示する。
     * 
     * @param form   入力用form
     * @param model  モデル
     * @param env_id 詳細情報を表示するenv_id
     * @return z/homeLayout
     */
    @GetMapping("/envDetail/{id}")
    public String getEnvDetail(@ModelAttribute InputEnvForm form, Model model, @PathVariable("id") String env_id) {

        // コンテンツ部分に環境マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/envDetail :: envDetail_contents");

        // 環境IDのチェック
        if (env_id != null && StrUtils.getStrLength(env_id) > 0) {

            // 環境マスタ情報を取得
            Env env = envService.selectOne(env_id);

            // Envクラスをフォームクラスに変換
            form.setEnv_id(env.getEnv_id()); // 環境ID
            form.setEnv_kbn(env.getEnv_kbn()); // 環境区分
            form.setEnv_num(env.getEnv_num()); // 環境連番
            form.setEnv_txt(env.getEnv_txt()); // 環境値
            form.setBiko(env.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputEnvForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 環境マスタ詳細画面の環境マスタ更新用処理.<BR>
     * 
     * 画面から入力した環境マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getEnvList(model)
     */
    @PostMapping(value = "/envDetail", params = "update")
    public String postEnvDetailUpdate(@ModelAttribute @Validated InputEnvForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、環境マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、環境マスタ詳細画面に戻ります
            return getEnvDetail(form, model, "");
        }

        // Envインスタンスの生成
        Env env = new Env();

        env.setEnv_id(form.getEnv_id()); // 環境ID
        env.setEnv_kbn(form.getEnv_kbn()); // 環境区分
        env.setEnv_num(form.getEnv_num()); // 環境連番
        env.setEnv_txt(form.getEnv_txt()); // 環境値
        env.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        env.setUpdate_user(user_auth.getUsername()); // 更新者

        // 更新実行
        boolean result = envService.updateOne(env);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            log.info("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            log.error("更新失敗");
        }

        // 環境マスタ一覧画面を表示
        return getEnvList(model);
    }

    /**
     * 環境マスタ詳細画面の環境マスタ削除用処理.<BR>
     * 
     * 画面から入力した環境マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getEnvList(model)
     */
    @PostMapping(value = "/envDetail", params = "delete")
    public String postEnvDetailDelete(@ModelAttribute InputEnvForm form, Model model) {

        // 削除実行
        boolean result = envService.deleteOne(form.getEnv_id());

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // 環境マスタ一覧画面を表示
        return getEnvList(model);
    }

    /**
     * 環境マスタ詳細画面の戻る処理.<BR>
     * 
     * 環境マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getEnvList(model)
     */
    @PostMapping(value = "/envDetail", params = "back")
    public String postEnvDetailback(Model model) {
        // 環境マスタ一覧画面を表示
        return getEnvList(model);
    }
}