package com.portal.z.common.domain.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.portal.z.common.domain.model.AppUserDetails;

@Repository
public class LoginUserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    /** ユーザー情報を取得するSQL */
    private static final String SELECT_USER_SQL
            = " select"
    		+ "    user_id"
    		+ "   ,user_due_date"
    		+ "   ,password"
    		+ "   ,pass_update"
    		+ "   ,login_miss_times"
    		+ "   ,lock_flg"
    		+ "   ,enabled_flg"
            + " from"
            + "   zm001_user"
            + " where"
            + "   user_id = ?";

    /** 権限リストを取得するSQL */
    private static final String SELECT_USER_ROLE_SQL
            = " select"
            + "   coalesce(C.role_name,'NOTHING') as role_name"
            + " from"
            + "   zm001_user as A"
            + " left join zm003_userrole as B"
            + "   on A.user_id = B.user_id"
            + " left join zm002_role as C"
            + "   on B.role_id = C.role_id"
            + " where"
            + "   A.user_id = ?";

    /**
     * ユーザー情報を取得して、UserDetailsを生成するメソッド.
     */
    public UserDetails selectOne(String userId) {

        //select実行(ユーザーの取得)
        Map<String, Object> userMap = jdbc.queryForMap(SELECT_USER_SQL, userId);

        //権限リストの取得（メソッド）
        List<GrantedAuthority> grantedAuthorityList = getRoleList(userId);

        // 結果返却用のUserDetailsを生成
        AppUserDetails user = buildUserDetails(userMap, grantedAuthorityList);

        return user;
    }

    /**
     * 権限リストを取得するメソッド.
     */
    private List<GrantedAuthority> getRoleList(String userId) {

        //select実行(ユーザー権限の取得)
        List<Map<String, Object>> authorityList =
                jdbc.queryForList(SELECT_USER_ROLE_SQL, userId);

        //結果返却用のList生成
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for(Map<String, Object> map: authorityList) {

            //ロール名を取得
            String roleName = (String)map.get("role_name");

            //SimpleGrantedAuthorityインスタンスの生成
            GrantedAuthority authority =
                    new SimpleGrantedAuthority(roleName);

            //結果返却用のListにインスタンスを追加
            grantedAuthorityList.add(authority);
        }

        return grantedAuthorityList;
    }

    /**
     * ユーザークラスの作成.
     */
    private AppUserDetails buildUserDetails(Map<String, Object> userMap,
            List<GrantedAuthority> grantedAuthorityList) {

        // Mapから値を取得
        String user_id       = (String) userMap.get("user_id");
        Date user_due_date   = (Date) userMap.get("user_due_date");
        String password      = (String) userMap.get("password");
        Date pass_update     = (Date) userMap.get("pass_update");
        int login_miss_times = (Integer) userMap.get("login_miss_times");
        boolean lock_flg     = (Boolean) userMap.get("lock_flg");
        boolean enabled_flg  = (Boolean) userMap.get("enabled_flg");

        // 結果返却用のUserDetailsを生成
        //AppUserDetails user = new AppUserDetails().builder()
        new AppUserDetails();
  		AppUserDetails user = AppUserDetails.builder()
                .user_id(user_id)
                .user_due_date(user_due_date)
                .password(password)
                .pass_update(pass_update)
                .login_miss_times(login_miss_times)
                .lock_flg(lock_flg)
                .enabled_flg(enabled_flg)
                .authority(grantedAuthorityList)
                .build();

        return user;
    }
}
