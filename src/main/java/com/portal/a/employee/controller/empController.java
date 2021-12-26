package com.portal.a.employee.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.model.Employeeattribute;
import com.portal.a.common.domain.model.Employeebelongs;
import com.portal.a.common.domain.model.Organization;
import com.portal.a.employee.domain.model.CreateOrder;
import com.portal.a.employee.domain.model.EmployeeListCsvView;
import com.portal.a.employee.domain.model.EmployeeListXlsxView;
import com.portal.a.employee.domain.model.InputEmployeeForm;
import com.portal.a.employee.domain.model.SelectEmployeeForm;
import com.portal.a.employee.domain.model.UpdateOrder;
import com.portal.a.employee.domain.service.EmployeeService;
import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Skill;
import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.model.User;
import com.portal.z.common.domain.service.RestSharedService;
import com.portal.z.common.domain.util.Constants;
import com.portal.z.common.domain.util.DateUtils;
import com.portal.z.common.domain.util.MassageUtils;
import com.portal.z.common.domain.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * employeeマスタ用のController
 * 
 */
@Controller
@Slf4j
public class empController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MassageUtils massageUtils;

    @Autowired
    RestSharedService restSharedService;

    /**
     * ラジオボタンの初期化メソッド.
     */
    private Map<String, String> initRadioGender_kbn() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 男、女をMapに格納
        radio.put("男　", Constants.GENDER_KBN_MAN);
        radio.put("女　", Constants.GENDER_KBN_WOMAN);

        return radio;
    }

    /**
     * 社員マスタ一覧画面のGETメソッド用処理.<BR>
     * 
     * 社員マスタ一覧画面を表示する。
     * 
     * @param model モデル
     * @return "z/homeLayout"
     */
    @GetMapping("/empList")
    public String getEmployeeList(Model model) {

        // 検索条件のformを登録
        SelectEmployeeForm form = new SelectEmployeeForm();
        model.addAttribute("selectEmployeeForm", form);

        // コンテンツ部分に社員マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/empList :: empList_contents");

        // 社員マスタ一覧の生成（退職者を表示しない）
        List<Employee> empList = employeeService.selectMany(null);

        // Modelにユーザーリストを登録
        model.addAttribute("empList", empList);

        // データ件数を取得
        int count = empList.size();
        model.addAttribute("empListCount", count);

        return "z/homeLayout";
    }

    /**
     * 社員マスタ一覧画面の社員検索用処理.<br>
     * 
     * 画面から入力した検索条件で社員マスタ情報を検索する。
     * 
     * @param form          検索条件のform
     * @param bindingResult 検索条件の入力値
     * @param model         モデル
     * @return z/homeLayout
     */
    @RequestMapping(value = "/empList", params = "selectby")
    public String getEmployeeListBy(@ModelAttribute SelectEmployeeForm form, BindingResult bindingResult, Model model) {

        // コンテンツ部分に社員マスタ一覧を表示するための文字列を登録
        model.addAttribute("contents", "a/empList :: empList_contents");

        // 退職者を表示する場合
        String leave_flg = null;
        if (form.isLeave_flg() == true) {
            leave_flg = Constants.LEAVE_FLG;
        }

        // 社員マスタ情報を取得
        List<Employee> empList = employeeService.selectBy(form.getEmployee_cd(), form.getEmployee_name1_last(),
                form.getMail(), form.getBiko(), leave_flg);

        // Modelに社員マスタリストを登録
        model.addAttribute("empList", empList);

        // データ件数を登録
        int count = empList.size();
        model.addAttribute("empListCount", count);

        return "z/homeLayout";
    }

    /**
     * 社員マスタ一覧のCSV出力用処理.<br>
     * 
     * 社員マスタ一覧のCSVファイルを出力する。
     * 
     * @param model モデル
     * @return ResponseEntity(bytes, header, HttpStatus.OK)
     */
    @GetMapping("/empList/csv")
    public EmployeeListCsvView getEmployeeListCsv(EmployeeListCsvView model) {

        // 社員マスタ一覧の生成
        List<Employee> employeeList = employeeService.selectMany(Constants.LEAVE_FLG);

        // Modelにユーザーリストを登録
        model.addStaticAttribute("employeeList", employeeList);

        return model;
    }

    /**
     * 社員マスタ一覧のExcel出力用処理.<br>
     * 
     * 社員マスタ一覧のEXCELファイルを出力する。
     * 
     * @param model モデル
     * @return model
     */
    @RequestMapping("/empList/excel")
    public EmployeeListXlsxView excel(EmployeeListXlsxView model) {

        // 社員マスタ一覧の生成
        List<Employee> employeeList = employeeService.selectMany(Constants.LEAVE_FLG);

        // Modelに社員マスタリストを登録
        model.addStaticAttribute("employeeList", employeeList);

        // データ件数を取得
        int count = employeeList.size();
        model.addStaticAttribute("employeeListCount", count);

        return model;
    }

    /**
     * 社員マスタ登録画面のGETメソッド用処理.<BR>
     * 
     * 社員情報の新規登録画面を表示する。
     * 
     * @param form  入力用のform
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/empUpdate")
    public String getSignUp(@ModelAttribute InputEmployeeForm form, Model model) {

        // コンテンツ部分に社員マスタ登録を表示するための文字列を登録
        model.addAttribute("contents", "a/empUpdate :: empUpdate_contents");

        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioGender_kbn", initRadioGender_kbn());

        // 社員属性一覧の生成
        List<Employeeattribute> employeeattribute = employeeService.selectManyemployeeattribute();
        // Modelに社員属性リストを登録
        model.addAttribute("employeeattributeList", employeeattribute);

        // 組織一覧の生成
        List<Organization> organization = employeeService.selectManyorganization();
        // Modelに組織リストを登録
        model.addAttribute("organizationList", organization);

        // 社員マスタ登録画面に画面遷移
        return "z/homeLayout";
    }

    /**
     * 社員マスタ登録画面のPOSTメソッド用処理.
     * 
     * @param form          フォーム
     * @param bindingResult 処理結果
     * @param model         モデル
     * @return 遷移先の情報(String)
     */
    @PostMapping("/empUpdate")
    public String postSignUp(@ModelAttribute @Validated(CreateOrder.class) InputEmployeeForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、社員マスタ登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
            return getSignUp(form, model);
        }

        // 生年月日チェック(生年月日 <= 入社日）
        if (form.getBirthday() != null) {
            if (DateUtils.compareDateTime(form.getBirthday().atStartOfDay(),
                    form.getJoined_date().atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "生年月日：" + form.getBirthday(), "入社日：" + form.getJoined_date() }));
                return getSignUp(form, model);
            }
        }

        // 郵便番号チェック（入力した郵便番号が実在しなかったとき）
        if (form.getPostcode() != null && StrUtils.getStrLength(form.getPostcode()) > 0) {
            /** 郵便番号検索API リクエストURL */
            String url = "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + form.getPostcode();
            // 郵便番号検索APIサービス呼び出し
            JSONObject getAddress = restSharedService.restget(url);

            if (getAddress.isNull("results")) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result",
                        massageUtils.getMsg("e.co.fw.2.004", new String[] { "郵便番号：" + form.getPostcode() }));
                return getSignUp(form, model);
            }
        }

        // 社員マスタinsert用変数
        Employee employee = new Employee();

        employee.setEmployee_cd(form.getEmployee_cd()); // 社員CD
        employee.setEmployee_name1_last(form.getEmployee_name1_last()); // 社員名漢字（姓）
        employee.setEmployee_name1_first(form.getEmployee_name1_first()); // 社員名漢字（名）
        employee.setEmployee_name2_last(form.getEmployee_name2_last()); // 社員名カナ（姓）
        employee.setEmployee_name2_first(form.getEmployee_name2_first()); // 社員名カナ（名）
        employee.setGender_kbn(form.getGender_kbn()); // 性別区分
        employee.setPostcode(StrUtils.gethalfwidthdigitfromfullwidthdigit(form.getPostcode())); // 郵便番号
        employee.setPrefcode(form.getPrefcode()); // 都道府県名CD
        employee.setPref_name1(form.getPref_name1()); // 都道府県名１
        employee.setPref_name2(form.getPref_name2()); // 都道府県名２
        employee.setCitycode(form.getCitycode()); // 市区町村名CD
        employee.setCity_name1(form.getCity_name1()); // 市区町村名１
        employee.setCity_name2(form.getCity_name2()); // 市区町村名２
        employee.setStreetaddress1(form.getStreetaddress1()); // 住所１
        employee.setStreetaddress2(form.getStreetaddress2()); // 住所２
        if (form.getBirthday() != null) {
            employee.setBirthday(Date.valueOf(form.getBirthday())); // 生年月日
        }
        employee.setNearest_station_code(form.getNearest_station_code()); // 最寄駅コード
        employee.setNearest_station_name(form.getNearest_station_name()); // 最寄駅名
        employee.setMail(form.getMail()); // メールアドレス
        employee.setJoined_date(Date.valueOf(form.getJoined_date())); // 入社日
        // 退社日は新規登録時は入力しない。
        employee.setEmployeeattribute_id(form.getEmployeeattribute_id()); // 社員属性ID
        employee.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        employee.setInsert_user(user_auth.getUsername()); // 作成者

        // 社員所属マスタinsert用変数
        Employeebelongs employeebelongs = new Employeebelongs();

        // 社員IDは社員マスタに登録後に取得する。
        employeebelongs.setStart_yearmonth(DateUtils.getStringFromDate(form.getJoined_date()).substring(0, 6)); // 開始年月
        employeebelongs.setOrganization_cd(form.getOrganization_cd()); // 組織CD
        employeebelongs.setInsert_user(user_auth.getUsername()); // 作成者

        // 社員マスタ、社員所属マスタ登録処理(employee、employeebelongs)
        try {
            boolean result = employeeService.insertOne(employee, employeebelongs);

            // 社員マスタ登録結果の判定
            if (result == true) {
                model.addAttribute("result", "登録成功");
                log.info("登録成功");
            } else {
                model.addAttribute("result", "登録失敗");
                log.error("登録失敗");
            }

        } catch (DuplicateKeyException e) {

            // 社員CD確認
            Employee employeeOne = employeeService.selectOneByEmployeecd(form.getEmployee_cd());
            if (employeeOne != null) {
                String message = "社員CD" + employee.getEmployee_cd();
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getSignUp(form, model);
            }

            // メール確認
            Employee employeeOneMail = employeeService.selectOneByMail(form.getMail());
            if (employeeOneMail != null) {
                String message = "メールアドレス" + employee.getMail();
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getSignUp(form, model);
            }

            // 一意制約エラーが発生した時はビジネス例外として返す。
            String message = "入力した社員情報";
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getSignUp(form, model);
        }

        // 社員マスタ一覧画面を表示
        return getEmployeeList(model);
    }

    /**
     * 社員マスタ登録画面の戻る処理.<BR>
     * 
     * 社員マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getEmployeeList(model)
     */
    @PostMapping(value = "/empUpdate", params = "back")
    public String postEmployeeUpdateback(Model model) {
        // 社員マスタ一覧画面を表示
        return getEmployeeList(model);
    }

    /**
     * 社員マスタ詳細画面のGETメソッド用処理.<BR>
     * 
     * 社員マスタ詳細画面を表示する。
     * 
     * @param form        入力用form
     * @param model       モデル
     * @param employee_id 詳細情報を表示するemployee_id
     * @return z/homeLayout
     */
    @GetMapping("/empDetail/{id}")
    public String getEmployeeDetail(@ModelAttribute InputEmployeeForm form, Model model,
            @PathVariable("id") String employee_id) {

        // コンテンツ部分に社員マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/empDetail :: empDetail_contents");

        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioGender_kbn", initRadioGender_kbn());

        // 社員属性一覧の生成
        List<Employeeattribute> employeeattributeList = employeeService.selectManyemployeeattribute();
        // Modelに社員属性リストを登録
        model.addAttribute("employeeattributeList", employeeattributeList);

        // 組織一覧の生成
        List<Organization> organizationList = employeeService.selectManyorganization();
        // Modelに組織リストを登録
        model.addAttribute("organizationList", organizationList);

        // 社員IDのチェック
        if (employee_id != null && StrUtils.getStrLength(employee_id) > 0) {

            // 社員マスタ情報を取得
            Employee employee = employeeService.selectOne(employee_id);

            // Employeeクラスをフォームクラスに変換
            form.setEmployee_id(employee.getEmployee_id()); // 社員ID
            form.setEmployee_cd(employee.getEmployee_cd()); // 社員CD
            form.setEmployee_name1_last(employee.getEmployee_name1_last()); // 社員名漢字（姓）
            form.setEmployee_name1_first(employee.getEmployee_name1_first()); // 社員名漢字（名）
            form.setEmployee_name2_last(employee.getEmployee_name2_last()); // 社員名カナ（姓）
            form.setEmployee_name2_first(employee.getEmployee_name2_first()); // 社員名カナ（名）
            form.setGender_kbn(employee.getGender_kbn()); // 性別区分
            form.setPostcode(employee.getPostcode()); // 郵便番号
            form.setPrefcode(employee.getPrefcode()); // 都道府県名CD
            form.setPref_name1(employee.getPref_name1()); // 都道府県名１
            form.setPref_name2(employee.getPref_name2()); // 都道府県名２
            form.setCitycode(employee.getCitycode()); // 市区町村名CD
            form.setCity_name1(employee.getCity_name1()); // 市区町村名１
            form.setCity_name2(employee.getCity_name2()); // 市区町村名２
            form.setStreetaddress1(employee.getStreetaddress1()); // 住所１
            form.setStreetaddress2(employee.getStreetaddress2()); // 住所２
            if (employee.getBirthday() != null) {
                form.setBirthday(employee.getBirthday().toLocalDate()); // 生年月日
            }
            form.setNearest_station_code(employee.getNearest_station_code()); // 最寄駅コード
            form.setNearest_station_name(employee.getNearest_station_name()); // 最寄駅名
            form.setMail(employee.getMail()); // メールアドレス
            if (employee.getJoined_date() != null) {
                form.setJoined_date(employee.getJoined_date().toLocalDate()); // 入社日
            }
            if (employee.getLeave_date() != null) {
                form.setLeave_date(employee.getLeave_date().toLocalDate()); // 退社日
            }
            form.setEmployeeattribute_id(employee.getEmployeeattribute_id()); // 社員属性ID
            form.setOrganization_cd(employee.getOrganization_cd()); // 組織CD
            form.setStart_yearmonth(employee.getStart_yearmonth()); // 開始年月
            form.setBiko(employee.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputEmployeeForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 社員マスタ詳細画面の社員マスタ更新用処理.<BR>
     * 
     * 画面から入力した社員マスタ情報でデータを更新する。
     * 
     * @param form          入力用form
     * @param bindingResult 更新する情報
     * @param model         モデル
     * @return getEmployeeList(model)
     */
    @PostMapping(value = "/empDetail", params = "update")
    public String postEmployeeDetailUpdate(@ModelAttribute @Validated(UpdateOrder.class) InputEmployeeForm form,
            BindingResult bindingResult, Model model) {

        // 入力チェックに引っかかった場合、社員マスタ詳細画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、社員マスタ詳細画面に戻ります
            return getEmployeeDetail(form, model, "");
        }

        // 生年月日チェック(生年月日 <= 入社日）
        if (form.getBirthday() != null) {
            if (DateUtils.compareDateTime(form.getBirthday().atStartOfDay(),
                    form.getJoined_date().atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "生年月日：" + form.getBirthday(), "入社日：" + form.getJoined_date() }));
                return getEmployeeDetail(form, model, "");
            }
        }

        // 退社日チェック
        if (form.getLeave_date() != null) {
            // 入社日 <= 退社日
            if (DateUtils.compareDateTime(form.getJoined_date().atStartOfDay(),
                    form.getLeave_date().atStartOfDay()) == 1) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result", massageUtils.getMsg("e.co.fw.1.022",
                        new String[] { "入社日：" + form.getJoined_date(), "退社日：" + form.getLeave_date() }));
                return getEmployeeDetail(form, model, "");
            }

            // ユーザマスタに登録されている場合は、先にユーザマスタを削除すること
            User selectByEmployeeid = employeeService.selectByEmployeeid(form.getEmployee_id());
            if (selectByEmployeeid != null) {
                String message = "この社員はユーザ情報が登録されているので退職日を登録できません。先にユーザ情報を削除するなどのメンテナンスをして下さい。";
                model.addAttribute("result", message);
                return getEmployeeDetail(form, model, "");
            }
        }

        // 郵便番号チェック（入力した郵便番号が実在しなかったとき）
        if (form.getPostcode() != null && StrUtils.getStrLength(form.getPostcode()) > 0) {
            /** 郵便番号検索API リクエストURL */
            String url = "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + form.getPostcode();
            // 郵便番号検索APIサービス呼び出し
            JSONObject getAddress = restSharedService.restget(url);

            if (getAddress.isNull("results")) {
                // GETリクエスト用のメソッドを呼び出して、社員マスタ登録画面に戻ります
                model.addAttribute("result",
                        massageUtils.getMsg("e.co.fw.2.004", new String[] { "郵便番号：" + form.getPostcode() }));
                return getEmployeeDetail(form, model, "");
            }
        }

        // Employeeインスタンスの生成
        Employee employee = new Employee();

        employee.setEmployee_id(form.getEmployee_id()); // 社員ID
        employee.setEmployee_cd(form.getEmployee_cd()); // 社員CD
        employee.setEmployee_name1_last(form.getEmployee_name1_last()); // 社員名漢字（姓）
        employee.setEmployee_name1_first(form.getEmployee_name1_first()); // 社員名漢字（名）
        employee.setEmployee_name2_last(form.getEmployee_name2_last()); // 社員名カナ（姓）
        employee.setEmployee_name2_first(form.getEmployee_name2_first()); // 社員名カナ（名）
        employee.setGender_kbn(form.getGender_kbn()); // 性別区分
        employee.setPostcode(StrUtils.gethalfwidthdigitfromfullwidthdigit(form.getPostcode())); // 郵便番号
        employee.setPrefcode(form.getPrefcode()); // 都道府県名CD
        employee.setPref_name1(form.getPref_name1()); // 都道府県名１
        employee.setPref_name2(form.getPref_name2()); // 都道府県名２
        employee.setCitycode(form.getCitycode()); // 市区町村名CD
        employee.setCity_name1(form.getCity_name1()); // 市区町村名１
        employee.setCity_name2(form.getCity_name2()); // 市区町村名２
        employee.setStreetaddress1(form.getStreetaddress1()); // 住所１
        employee.setStreetaddress2(form.getStreetaddress2()); // 住所２
        if (form.getBirthday() != null) {
            employee.setBirthday(Date.valueOf(form.getBirthday())); // 生年月日
        }
        employee.setNearest_station_code(form.getNearest_station_code()); // 最寄駅コード
        employee.setNearest_station_name(form.getNearest_station_name()); // 最寄駅名
        employee.setMail(form.getMail()); // メールアドレス
        if (form.getJoined_date() != null) {
            employee.setJoined_date(Date.valueOf(form.getJoined_date())); // 入社日
        }
        if (form.getLeave_date() != null) {
            employee.setLeave_date(Date.valueOf(form.getLeave_date())); // 退社日
        }
        employee.setEmployeeattribute_id(form.getEmployeeattribute_id()); // 社員属性ID
        employee.setOrganization_cd(form.getOrganization_cd()); // 組織CD
        employee.setBiko(form.getBiko()); // 備考

        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        employee.setUpdate_user(user_auth.getUsername()); // 更新者

        // 社員所属マスタupdate用変数
        Employeebelongs employeebelongs = new Employeebelongs();

        employeebelongs.setEmployee_id(form.getEmployee_id());// 社員ID
        employeebelongs.setStart_yearmonth(DateUtils.getStringFromDate(LocalDate.now()).substring(0, 6));// 開始年月
        employeebelongs.setOrganization_cd(form.getOrganization_cd());// 組織CD
        employeebelongs.setInsert_user(user_auth.getUsername()); // 作成者
        employeebelongs.setUpdate_user(user_auth.getUsername()); // 更新者

        try {
            // 更新実行
            boolean result = employeeService.updateOne(employee, employeebelongs);

            if (result == true) {
                model.addAttribute("result", "更新成功");
                log.info("更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
                log.error("更新失敗");
            }

        } catch (DuplicateKeyException e) {

            // 社員CD確認
            Employee employeeOne = employeeService.selectOneByEmployeecd(form.getEmployee_cd());
            if ((employeeOne != null) && (!employeeOne.getEmployee_id().equals(form.getEmployee_id()))) {
                // 他の社員IDが同じ社員CDを持っている
                String message = "社員CD" + employee.getEmployee_cd();
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getEmployeeDetail(form, model, "");
            }

            // メール確認
            Employee employeeOneMail = employeeService.selectOneByMail(form.getMail());
            if ((employeeOneMail != null) && (!employeeOneMail.getEmployee_id().equals(form.getEmployee_id()))) {
                // 他の社員IDが同じメールを持っている
                String message = "メールアドレス" + employee.getMail();
                String messageKey = "e.co.fw.2.003";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getEmployeeDetail(form, model, "");
            }

            // 一意制約エラーが発生した時はビジネス例外として返す。
            String message = "入力した社員情報";
            String messageKey = "e.co.fw.2.003";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getEmployeeDetail(form, model, "");
        }

        // 社員マスタ一覧画面を表示
        return getEmployeeList(model);
    }

    /**
     * 社員マスタ詳細画面の社員マスタ削除用処理.<BR>
     * 
     * 画面から入力した社員マスタ情報でデータを削除する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return getEmployeeList(model)
     */
    @PostMapping(value = "/empDetail", params = "delete")
    public String postEmployeeDetailDelete(@ModelAttribute InputEmployeeForm form, Model model) {

        try {
            // 削除実行
            boolean result = employeeService.deleteOne(form.getEmployee_id());

            if (result == true) {
                model.addAttribute("result", "削除成功");
                log.info("削除成功");
            } else {
                model.addAttribute("result", "削除失敗");
                log.error("削除失敗");
            }

        } catch (DataIntegrityViolationException e) {

            // スキル情報確認
            Skill selectSkillOne = employeeService.selectSkillOne(form.getEmployee_id());
            if (selectSkillOne.getEmployee_id_skill() != null) {
                String message = "この社員はスキル情報";
                String messageKey = "e.co.fw.2.023";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getEmployeeDetail(form, model, "");
            }

            // 業務経歴情報確認
            List<Career> careerList = employeeService.selectCareerBy(form.getEmployee_id());
            if (!careerList.isEmpty()) {
                String message = "この社員は業務経歴情報";
                String messageKey = "e.co.fw.2.023";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getEmployeeDetail(form, model, "");
            }

            // ユーザマスタ登録確認
            User selectByEmployeeid = employeeService.selectByEmployeeid(form.getEmployee_id());
            if (selectByEmployeeid != null) {
                String message = "この社員はユーザ情報";
                String messageKey = "e.co.fw.2.023";
                model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
                return getEmployeeDetail(form, model, "");
            }

            // 参照整合性エラーが発生した時はビジネス例外として返す。
            String message = "この社員は役職";
            String messageKey = "e.co.fw.2.023";
            model.addAttribute("result", massageUtils.getMsg(messageKey, new String[] { message }));
            return getEmployeeDetail(form, model, "");
        }

        // 社員マスタ一覧画面を表示
        return getEmployeeList(model);
    }

    /**
     * 社員マスタ詳細画面の戻る処理.<BR>
     * 
     * 社員マスタ一覧画面に戻る。
     * 
     * @param model モデル
     * @return getemployeeList(model)
     */
    @PostMapping(value = "/empDetail", params = "back")
    public String postEmployeeDetailback(Model model) {
        // 社員マスタ一覧画面を表示
        return getEmployeeList(model);
    }

    /**
     * 社員マスタ個人画面のGETメソッド用処理.<BR>
     * 
     * 社員マスタ個人画面を表示する。
     * 
     * @param form  入力用form
     * @param model モデル
     * @return z/homeLayout
     */
    @GetMapping("/empPerson")
    public String getEmployeePerson(@ModelAttribute InputEmployeeForm form, Model model) {

        // コンテンツ部分に社員マスタ詳細を表示するための文字列を登録
        model.addAttribute("contents", "a/empPerson :: empPerson_contents");

        // ラジオボタンのMapを初期化してModelに登録
        model.addAttribute("radioGender_kbn", initRadioGender_kbn());

        // 社員属性一覧の生成
        List<Employeeattribute> employeeattributeList = employeeService.selectManyemployeeattribute();
        // Modelに社員属性リストを登録
        model.addAttribute("employeeattributeList", employeeattributeList);

        // 組織一覧の生成
        List<Organization> organizationList = employeeService.selectManyorganization();
        // Modelに組織リストを登録
        model.addAttribute("organizationList", organizationList);

        // 社員IDを取得
        // ログインユーザー情報の取得
        AppUserDetails user_auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String user_id = user_auth.getUsername();
        User user = employeeService.selectUserOne(user_id);

        // 社員IDのチェック
        if (user.getEmployee_id() != null && StrUtils.getStrLength(user.getEmployee_id()) > 0) {

            // 社員マスタ情報を取得
            Employee employee = employeeService.selectOne(user.getEmployee_id());

            // Employeeクラスをフォームクラスに変換
            form.setEmployee_id(employee.getEmployee_id()); // 社員ID
            form.setEmployee_cd(employee.getEmployee_cd()); // 社員CD
            form.setEmployee_name1_last(employee.getEmployee_name1_last()); // 社員名漢字（姓）
            form.setEmployee_name1_first(employee.getEmployee_name1_first()); // 社員名漢字（名）
            form.setEmployee_name2_last(employee.getEmployee_name2_last()); // 社員名カナ（姓）
            form.setEmployee_name2_first(employee.getEmployee_name2_first()); // 社員名カナ（名）
            form.setGender_kbn(employee.getGender_kbn()); // 性別区分
            form.setPostcode(employee.getPostcode()); // 郵便番号
            form.setPrefcode(employee.getPrefcode()); // 都道府県名CD
            form.setPref_name1(employee.getPref_name1()); // 都道府県名１
            form.setPref_name2(employee.getPref_name2()); // 都道府県名２
            form.setCitycode(employee.getCitycode()); // 市区町村名CD
            form.setCity_name1(employee.getCity_name1()); // 市区町村名１
            form.setCity_name2(employee.getCity_name2()); // 市区町村名２
            form.setStreetaddress1(employee.getStreetaddress1()); // 住所１
            form.setStreetaddress2(employee.getStreetaddress2()); // 住所２
            if (employee.getBirthday() != null) {
                form.setBirthday(employee.getBirthday().toLocalDate()); // 生年月日
            }
            form.setNearest_station_code(employee.getNearest_station_code()); // 最寄駅コード
            form.setNearest_station_name(employee.getNearest_station_name()); // 最寄駅名
            form.setMail(employee.getMail()); // メールアドレス
            if (employee.getJoined_date() != null) {
                form.setJoined_date(employee.getJoined_date().toLocalDate()); // 入社日
            }
            if (employee.getLeave_date() != null) {
                form.setLeave_date(employee.getLeave_date().toLocalDate()); // 退社日
            }
            form.setEmployeeattribute_id(employee.getEmployeeattribute_id()); // 社員属性ID
            form.setOrganization_cd(employee.getOrganization_cd()); // 組織CD
            form.setStart_yearmonth(employee.getStart_yearmonth()); // 開始年月
            form.setBiko(employee.getBiko()); // 備考

            // Modelに登録
            model.addAttribute("inputEmployeeForm", form);
        }

        return "z/homeLayout";
    }

    /**
     * 社員マスタ個人画面の戻る処理.<BR>
     * 
     * home画面に戻る。
     * 
     * @param model モデル
     * @return redirect:/home
     */
    @PostMapping(value = "/empPerson", params = "back")
    public String postEmployeePersonback(Model model) {
        // ホーム画面に遷移
        return "redirect:/home";
    }

}