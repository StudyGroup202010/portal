package com.portal.z.user.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.z.user.domain.model.GroupOrder;
import com.portal.z.user.domain.model.InputForm;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.model.Userrole;
import com.portal.z.common.domain.model.Role;
import com.portal.z.common.domain.service.UserService;
import com.portal.z.common.domain.service.UserroleService;
import com.portal.z.common.domain.service.RoleService;
import com.portal.z.login.domain.model.AppUserDetails;

@Transactional
@Controller
public class userController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserroleService userroleService;
    
    @Autowired
    private RoleService roleService;
    
    //パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    //ラジオボタン用変数
    private Map<String, String> radioEnabled;
    private Map<String, String> radioLock;

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
     * ユーザー一覧画面のGETメソッド用処理.
     */
    @GetMapping("/userList")
    public String getUserList(Model model) {

        //コンテンツ部分にユーザー一覧を表示するための文字列を登録
        model.addAttribute("contents", "z/userList :: userList_contents");

        //ユーザー一覧の生成
        List<User> userList = userService.selectMany();

        //Modelにユーザーリストを登録
        model.addAttribute("userList", userList);

        //データ件数を取得
        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "z/homeLayout";
    }
    
    /**
     * ユーザー一覧のCSV出力用処理.
     */
    @GetMapping("/userList/csv")
    public ResponseEntity<byte[]> getUserListCsv(Model model) {
    	
        //ユーザーを全件取得して、CSVをサーバーに保存する
        userService.userCsvOut();
        
        byte[] bytes = null;

        try {

            //サーバーに保存されているcsvファイルをbyteで取得する
            bytes = userService.getFile("userlist.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //HTTPヘッダーの設定
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=UTF-8");
        header.setContentDispositionFormData("filename", "userlist.csv");

        //csvを戻す
        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }
    
    /**
     * ユーザー登録画面のGETメソッド用処理.
     */
    @GetMapping("/userUpdate")
    public String getSignUp(@ModelAttribute InputForm form, Model model) {
    	
        // コンテンツ部分にユーザー登録を表示するための文字列を登録
        model.addAttribute("contents", "z/userUpdate :: userUpdate_contents");

        // ラジオボタンの初期化メソッド呼び出し
        radioEnabled = initRadioEnabled();
        radioLock    = initRadioLock();
        
        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioEnabled", radioEnabled);
        model.addAttribute("radioLock", radioLock);
        
        // userUpdate.htmlに画面遷移
        return "z/homeLayout";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/userUpdate")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) InputForm form,
            BindingResult bindingResult,
            Model model) {
    	
        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {

            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
            return getSignUp(form, model);

        }
        
        // ユーザマスタinsert用変数
        User user = new User();

        user.setUser_id(form.getUser_id());                   //ユーザーID
        user.setUser_due_date(form.getUser_due_date());       //ユーザ有効期限
        //パスワードは暗号化する
        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);                           //パスワード
        user.setPass_update(form.getPass_update());           //パスワード有効期限
        //ロールとログイン失敗回数はテーブルの初期値にて設定される
        user.setLock_flg(form.isLock_flg());                  //ロック状態
        user.setEnabled_flg(form.isEnabled_flg());            //有効フラグ
        
        //ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        
        user.setInsert_user(user_auth.getUsername());         //作成者
        
        // ユーザロールマスタinsert用変数
        Userrole userrole = new Userrole();
        
        // 環境マスタに登録したロール名（一般ユーザ）のrole_idを取得
        Role role = roleService.selectRoleid("ROLE_NAME_G");
        //
        //ToDo
        //ここでrole取得結果を評価しないといけない
        //環境マスタに未登録の時やrole_idが取れなかったときは以降の処理を中止するなど
        //

        userrole.setUser_id(form.getUser_id());               //ユーザーID
        userrole.setRole_id(role.getRole_id());               //ロールID
        
        // ユーザー登録処理
        boolean result_1 = userService.insert(user);
        boolean result_2 = userroleService.insert(userrole);
        		
        // ユーザー登録結果の判定
        if (result_1 == true && result_2 == true ) {
        	model.addAttribute("result", "登録成功");
            System.out.println("登録成功");
        } else {
        	model.addAttribute("result", "登録失敗");
            System.out.println("登録失敗");
        }

      //ユーザー一覧画面を表示
        return getUserList(model);
    }
    
    /**
     * ユーザー登録画面の戻る処理.
     */
    @PostMapping(value = "/userUpdate", params = "back")
    public String postUserUpdateback(@ModelAttribute InputForm form,
            Model model) {

        //ユーザー一覧画面を表示
        return getUserList(model);
    }
    
    /**
     * ユーザー詳細画面のGETメソッド用処理.
     */
    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute InputForm form,
            Model model,
            @PathVariable("id") String user_id) {

        // コンテンツ部分にユーザー詳細を表示するための文字列を登録
        model.addAttribute("contents", "z/userDetail :: userDetail_contents");

        // 結婚ステータス用ラジオボタンの初期化
        radioEnabled = initRadioEnabled();
        radioLock = initRadioLock();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioEnabled", radioEnabled);
        model.addAttribute("radioLock", radioLock);

        // ユーザーIDのチェック
        if (user_id != null && user_id.length() > 0) {

            // ユーザー情報を取得
            User user = userService.selectOne(user_id);

            // Userクラスをフォームクラスに変換
            form.setUser_id(user.getUser_id());                   //ユーザーID
            form.setUser_due_date(user.getUser_due_date());       //ユーザ有効期限
            form.setPass_update(user.getPass_update());           //パスワード有効期限
            form.setLogin_miss_times(user.getLogin_miss_times()); //ログイン失敗回数
            form.setLock_flg(user.isLock_flg());                  //ロック状態
            form.setEnabled_flg(user.isEnabled_flg());            //有効フラグ

            // Modelに登録
            model.addAttribute("inputForm", form);
        }

        return "z/homeLayout";
        
    }

    /**
     * ユーザー詳細画面のユーザー更新用処理.
     */
    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute @Validated(GroupOrder.class) InputForm form,
    		BindingResult bindingResult,
            Model model) {

        // 入力チェックに引っかかった場合、ユーザー詳細画面に戻る
        if (bindingResult.hasErrors()) {

            // GETリクエスト用のメソッドを呼び出して、ユーザ詳細画面に戻ります
        	return getUserDetail(form, model,"");
        }

        //Userインスタンスの生成
        User user = new User();

        //フォームクラスをUserクラスに変換
        user.setUser_id(form.getUser_id());                   //ユーザーID
        user.setUser_due_date(form.getUser_due_date());       //ユーザ有効期限
        //パスワードは暗号化する
        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);                           //パスワード
        user.setPass_update(form.getPass_update());           //パスワード有効期限
        //権限は変更できない
        user.setLogin_miss_times(form.getLogin_miss_times()); //ログイン失敗回数
        user.setLock_flg(form.isLock_flg());                  //ロック状態
        user.setEnabled_flg(form.isEnabled_flg());            //有効フラグ
        
        //ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        
        user.setUpdate_user(user_auth.getUsername());         //更新者

        //更新実行
        boolean result = userService.updateOne(user);

        if (result == true) {
            model.addAttribute("result", "更新成功");
            System.out.println("更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
            System.out.println("更新失敗");
        }

        //ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザー詳細画面のユーザー削除用処理.
     */
    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute InputForm form,
            Model model) {

        //削除実行
        boolean result_1 = userroleService.deleteOne(form.getUser_id());
        boolean result_2 = userService.deleteOne(form.getUser_id());

        if (result_1 == true && result_2 == true) {

            model.addAttribute("result", "削除成功");
            System.out.println("削除成功");

        } else {

            model.addAttribute("result", "削除失敗");
            System.out.println("削除失敗");

        }

        //ユーザー一覧画面を表示
        return getUserList(model);
    }
    
    /**
     * ユーザー詳細画面の戻る処理.
     */
    @PostMapping(value = "/userDetail", params = "back")
    public String postUserDetailback(@ModelAttribute InputForm form,
            Model model) {

        //ユーザー一覧画面を表示
        return getUserList(model);
    }

    /**
     * DataAccessException発生時の処理メソッド.
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "userControllerでDataAccessExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /**
     * Exception発生時の処理メソッド.
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバーエラー：ExceptionHandler");

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("message", "userControllerでExceptionが発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}