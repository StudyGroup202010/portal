package com.portal.z.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.a.common.domain.model.Employee;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.UserSharedService;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;
import com.portal.z.common.exception.ApplicationException;
import com.portal.z.user.domain.model.CreateOrder;
import com.portal.z.user.domain.model.InputForm;
import com.portal.z.user.domain.model.SelectForm;
import com.portal.z.user.domain.model.UpdateOrder;
import com.portal.z.user.domain.model.UserListCsvView;
import com.portal.z.user.domain.model.UserListXlsxView;
import com.portal.z.user.domain.model.UserReportXlsxView;
import com.portal.z.user.domain.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * userマスタ用のController
 * 
 */
@Controller
@Slf4j
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSharedService userSharedService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MassageUtils massageUtils;

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

    private Map<String, String> initRadioLock() {

        Map<String, String> radio = new LinkedHashMap<>();

        // ロック、アンロックをMapに格納
        radio.put("アンロック", "false");
        radio.put("ロック", "true");

        return radio;
    }

    /**
     * ユーザー一覧画面のGETメソッド用処理.<BR>
     * 
     * ユーザ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/userList")
    public String getUserList(Model model) {

        // 検索条件のformを登録
        SelectForm form = new SelectForm();
        model.addAttribute("selectForm", form);

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "z/userList :: userList_contents");

        // ユーザー一覧の生成
        List<User> userList = userService.selectMany();

        // Modelにユーザーリストを登録
        model.addAttribute("userList", userList);

        // データ件数を取得
        int count = userList.size();
        model.addAttribute("userListCount", count);

        return "z/homeLayout";
    }

    /**
     * ユーザー一覧画面のユーザー検索用処理.<br>
     * 
     * 画面から入力した検索条件でユーザ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/userList", params = "selectby")
    public String getUserListByUserid(@ModelAttribute SelectForm form, BindingResult bindingResult, Model model) {

        // コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "z/userList :: userList_contents");

        // ユーザー情報を取得
        // 日付項目は未入力時の対処が必要なので、ユーティリティを使います。
        List<User> userList = userService.selectBy(form.getUser_id(),
                DateUtils.getStringFromDate(DateUtils.setStartDate(form.getUser_due_date_from())),
                DateUtils.getStringFromDate(DateUtils.setEndDate(form.getUser_due_date_to())));

        // Modelにユーザーリストを登録
        model.addAttribute("userList", userList);

        // データ件数を登録
        int count = userList.size();
        model.addAttribute("userListCount", count);

        return "z/homeLayout";
    }

    /**
     * ユーザー一覧のCSV出力用処理.<br>
     * 
     * ユーザ一覧のCSVファイルを出力する。
     * 
     * @param model モデル
     * @return ResponseEntity(bytes, header, HttpStatus.OK)
     */
    @GetMapping("/userList/csv")
    public UserListCsvView getUserListCsv(UserListCsvView model) {

        // ユーザー一覧の生成
        List<User> userList = userService.selectMany();

        // Modelにユーザーリストを登録
        model.addStaticAttribute("userList", userList);

        return model;
    }

    /**
     * ユーザー一覧のExcel出力用処理.<br>
     * 
     * ユーザ一覧のEXCELファイルを出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/userList/excel")
    public UserListXlsxView excel(UserListXlsxView model) {

        // ユーザー一覧の生成
        List<User> userList = userService.selectMany();

        // Modelにユーザーリストを登録
        model.addStaticAttribute("userList", userList);

        // データ件数を取得
        int count = userList.size();
        model.addStaticAttribute("userListCount", count);

        return model;
    }

    /**
     * ユーザー一覧のExcelアップロード用処理.<br>
     * 
     * ユーザ一覧のエクセルアップロードをする。
     * 
     * @param file  アップロードしたいEXCELファイル
     * @param model モデル
     * @return getUserList(model)
     * @throws EncryptedDocumentException EncryptedDocumentException
     * @throws IOException                アップロードしたいエクセルファイルの読み込みエラー
     */
    @RequestMapping(value = "/userList/excelUpload", params = "excelUpload")
    public String uploadfile(@RequestParam("uploadfile") MultipartFile file, Model model)
            throws EncryptedDocumentException, IOException {

        // メッセージを初期化
        model.addAttribute("result", null);

        // ユーザー登録処理(user)
        try {
            boolean result = userService.insertFromExcel(file, "ユーザマスタ");

            // ユーザー登録結果の判定
            if (result == true) {
                model.addAttribute("result", "登録成功");
                log.info("登録成功");
            } else {
                model.addAttribute("result", "エクセルデータが登録されませんでした。");
                log.error("登録失敗");
            }
        } catch (ApplicationException e) {
            model.addAttribute("result", e.getMessage());
        }

        // ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー一覧のExcel帳票出力用処理.<br>
     * 
     * ユーザ一覧の帳票を出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/userList/report")
    public UserReportXlsxView report(UserReportXlsxView model) {

        // ユーザー一覧の生成
        List<User> userList = userService.selectMany();

        // Modelにユーザーリストを登録
        model.addStaticAttribute("userList", userList);

        // データ件数を取得
        int count = userList.size();
        model.addStaticAttribute("userListCount", count);

        // エクセルテンプレートファイルを指定
        model.addStaticAttribute("template", "userList.xlsx");

        return model;
    }

    /**
     * ユーザー登録画面のGETメソッド用処理.<BR>
     * 
     * ユーザ情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/userUpdate")
    // このメソッドを使える権限を付与する場合は以下のようにPreAuthorizeをつける
    // ただし、権限が無い場合はVIEWで非表示にした方が綺麗なので、実際はそちらで行う
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')") // ROLE_ADMIN権限のみ
    public String getSignUp(@ModelAttribute InputForm form, Model model) {

        // コンテンツ部分にユーザー登録を表示するための文字列を登録
        model.addAttribute("contents", "z/userUpdate :: userUpdate_contents");

        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioEnabled", initRadioEnabled());
        model.addAttribute("radioLock", initRadioLock());

        // プルダウンの内容を設定
        // 社員一覧の生成（退職者を除く）
        List<Employee> employeeList = userService.selectManyExceptRetireeEmployee();

        // Modelに社員リストを登録
        model.addAttribute("employeeList", employeeList);

        // ユーザ有効期限に初期設定
        form.setUser_due_date(
                DateUtils.calcDate(LocalDateTime.now(), "YYYY", Constants.USER_DUE_DATE_EXPIRATION_DATE).toLocalDate());
        // パスワード有効期限に本日を初期設定
        form.setPass_update(LocalDate.now());
        // 有効フラグを初期設定
        form.setEnabled_flg(true);
        // Modelに登録
        model.addAttribute("inputForm", form);

        // userUpdate.htmlに画面遷移
        return "z/homeLayout";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/userUpdate")
    public String postSignUp(@ModelAttribute @Validated(CreateOrder.class) InputForm form, BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {

            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
            return getSignUp(form, model);

        }

        // ユーザマスタinsert用変数
        User user = new User();

        user.setUser_id(form.getUser_id()); // ユーザーID
        user.setUser_due_date(Date.valueOf(form.getUser_due_date())); // ユーザ有効期限
        // パスワードは暗号化する
        String password = passwordEncoder.encode(Constants.INITIAL_PASSWORD);
        user.setPassword(password); // パスワード
        user.setPass_update(Date.valueOf(form.getPass_update())); // パスワード有効期限
        // ロック状態と有効フラグはテーブルの初期値にて設定される
        user.setLock_flg(form.isLock_flg()); // ロック状態
        user.setEmployee_id(form.getEmployee_id()); // 社員ID
        user.setEnabled_flg(form.isEnabled_flg()); // 有効フラグ

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        user.setInsert_user(user_auth.getUsername()); // 作成者

        // ユーザー登録処理(user)
        try {
            boolean result = userSharedService.insertOne(user);

            // ユーザー登録結果の判定
            if (result == true) {
                model.addAttribute("result", "登録成功");
                log.info("登録成功");
            } else {
                model.addAttribute("result", "登録失敗");
                log.error("登録失敗");
            }
        } catch (ApplicationException e) {
            model.addAttribute("result", e.getMessage());
            return getSignUp(form, model);

        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生した時はビジネス例外として返す。
            // ユーザID確認
            User userOne = userService.selectOne(form.getUser_id());
            if (userOne != null) {
                String message = "ユーザID " + form.getUser_id();
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getSignUp(form, model);
            }

            // 社員ID確認
            User userOneByEmployeeid = userService.selectOneByEmployeeid(form.getEmployee_id());
            if (userOneByEmployeeid != null) {
                String message = "社員";
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getSignUp(form, model);
            }

            String message = "入力したユーザ情報";
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getSignUp(form, model);

        } catch (DataIntegrityViolationException e) {
            // 参照整合性エラーが発生した時はビジネス例外として返す。
            String messageKey = "e.co.fw.2.004";
            String message = "社員マスタに社員ID " + user.getEmployee_id();
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getSignUp(form, model);

        }

        // ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー登録画面の戻る処理.<BR>
     * 
     * ユーザ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getUserList(model)
     */
    @PostMapping(value = "/userUpdate", params = "back")
    public String postUserUpdateback(Model model) {
        // ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー詳細画面のGETメソッド用処理.<BR>
     * 
     * ユーザ詳細画面を表示する。
     * 
     * @param form    入力用form
     * @param model   モデル
     * @param user_id 詳細情報を表示するuser_id
     * @return z/homeLayout
     */
    @GetMapping("/userDetail/{id:.+}")
    // user_idがメールアドレスなので正規表現（{id:.+}）で記述します。
    public String getUserDetail(@ModelAttribute InputForm form, Model model, @PathVariable("id") String user_id) {

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/userDetail :: userDetail_contents");

        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioEnabled", initRadioEnabled());
        model.addAttribute("radioLock", initRadioLock());

        // プルダウンの内容を設定
        // 社員一覧の生成（退職者を除く）
        List<Employee> employeeList = userService.selectManyExceptRetireeEmployee();

        // Modelに社員リストを登録
        model.addAttribute("employeeList", employeeList);

        // ユーザーIDのチェック
        if (user_id != null && StrUtils.getStrLength(user_id) > 0) {

            // ユーザー情報を取得
            User user = userService.selectOne(user_id);

            // Userクラスをフォームクラスに変換
            form.setUser_id(user.getUser_id()); // ユーザーID
            form.setUser_due_date(user.getUser_due_date().toLocalDate()); // ユーザ有効期限
            form.setPass_update(user.getPass_update().toLocalDate()); // パスワード有効期限
            form.setLogin_miss_times(user.getLogin_miss_times()); // ログイン失敗回数
            form.setLock_flg(user.isLock_flg()); // ロック状態
            form.setEmployee_id(user.getEmployee_id()); // 社員ID
            form.setEnabled_flg(user.isEnabled_flg()); // 有効フラグ

            // Modelに登録
            model.addAttribute("inputForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * ユーザー詳細画面のユーザー更新用処理.<BR>
     * 
     * 画面から入力したユーザ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getUserList(model)
     */
    @PostMapping(value = "/userDetail", params = "update")
    // ユーザ詳細画面は更新も削除も/userDetailにPOSTするため、どちらが押されたかを判断するために、
    // name属性の値をパラメータとして使っています
    public String postUserDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、ユーザー詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、ユーザ詳細画面に戻ります
            return getUserDetail(form, model, "");
        }

        // Userインスタンスの生成
        User user = new User();

        // フォームクラスをUserクラスに変換
        user.setUser_id(form.getUser_id()); // ユーザーID
        user.setUser_due_date(Date.valueOf(form.getUser_due_date())); // ユーザ有効期限
        user.setPass_update(Date.valueOf(form.getPass_update())); // パスワード有効期限
        // 権限は変更できない
        user.setLogin_miss_times(form.getLogin_miss_times()); // ログイン失敗回数
        user.setLock_flg(form.isLock_flg()); // ロック状態
        user.setEmployee_id(form.getEmployee_id()); // 社員ID
        user.setEnabled_flg(form.isEnabled_flg()); // 有効フラグ

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        user.setUpdate_user(user_auth.getUsername()); // 更新者

        try {
            // 更新実行
            boolean result = userService.updateOne(user);

            if (result == true) {
                model.addAttribute("result", "更新成功");
                log.info("更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
                log.error("更新失敗");
            }
        } catch (DuplicateKeyException e) {
            // 一意制約エラーが発生した時はビジネス例外として返す。
            String message = "社員";
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getUserDetail(form, model, "");

        } catch (DataIntegrityViolationException e) {
            // 参照整合性エラーが発生した時はビジネス例外として返す。
            String messageKey = "e.co.fw.2.004";
            String message = "社員マスタに社員ID " + user.getEmployee_id();
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getUserDetail(form, model, "");

        }

        // ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー詳細画面のユーザー削除用処理.<BR>
     * 
     * 画面から入力したユーザ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getUserList(model)
     */
    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute InputForm form, Model model) {

        // 削除実行
        boolean result = userSharedService.deleteOne(form.getUser_id());

        if (result == true) {
            model.addAttribute("result", "削除成功");
            log.info("削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
            log.error("削除失敗");
        }
        // ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー詳細画面の戻る処理.<BR>
     * 
     * ユーザ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getUserList(model)
     */
    @PostMapping(value = "/userDetail", params = "back")
    public String postUserDetailback(Model model) {
        // ユーザー一覧画面を表示
        return getUserList(model);
    }
}