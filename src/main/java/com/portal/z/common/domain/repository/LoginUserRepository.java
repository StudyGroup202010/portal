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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.portal.z.common.domain.model.AppUserDetails;
import com.portal.z.common.domain.util.MassageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * ユーザ情報の取得<BR>
 * 
 * ユーザに紐づく権限の取得
 *
 */
@Repository
@Slf4j
public class LoginUserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MassageUtils massageUtils;

    /** ユーザー情報を取得するSQL */
    private static final String SELECT_USER_SQL = 
              " select" 
            + "    user_id" 
            + "   ,user_due_date"
            + "   ,password"
            + "   ,pass_update" 
            + "   ,login_miss_times" 
            + "   ,lock_flg" 
            + "   ,employee_id"
            + "   ,enabled_flg" 
            + " from"
            + "   zm001_user" 
            + " where" 
            + "   user_id = ?";

    /** 権限リストを取得するSQL */
    private static final String SELECT_USER_ROLE_SQL =
              " select" 
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
     * 
     * @param userId userId
     * @return user
     */
    public UserDetails selectOne(String userId) {

        // 権限リストの取得
        List<GrantedAuthority> grantedAuthorityList = getRoleList(userId);

        // 結果返却用のUserDetailsを生成
        return buildUserDetails(userId, grantedAuthorityList);
    }

    /**
     * 権限リストを取得するメソッド.
     * 
     * @param userId userId
     * @return grantedAuthorityList
     */
    private List<GrantedAuthority> getRoleList(String userId) {

        // 権限情報の取得
        // 権限が与えられていない場合は、検索結果が０件になりうる
        List<Map<String, Object>> authorityList = jdbc.queryForList(SELECT_USER_ROLE_SQL, userId);

        // 結果返却用のList生成
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for (Map<String, Object> map : authorityList) {

            // ロール名を取得
            String roleName = (String) map.get("role_name");

            // SimpleGrantedAuthorityインスタンスの生成
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);

            // 結果返却用のListにインスタンスを追加
            grantedAuthorityList.add(authority);
        }

        return grantedAuthorityList;
    }

    /**
     * ユーザークラスの作成.
     * 
     * @param userId               userId
     * @param grantedAuthorityList
     * @return user
     */
    private AppUserDetails buildUserDetails(String userId, List<GrantedAuthority> grantedAuthorityList) {

        // ユーザ情報を取得
        List<Map<String, Object>> userMapList = jdbc.queryForList(SELECT_USER_SQL, userId);

        if (userMapList.isEmpty()) {
            // ユーザマスタにユーザIDが登録されていなかったとき
            log.info("メソッド終了：buildUserDetails（ユーザＩＤ {} が未存在)", userId);
            throw new UsernameNotFoundException(massageUtils.getMsg("e.co.fw.1.009", null));
        }

        Map<String, Object> userMap = userMapList.get(0);
        // Mapから値を取得
        String user_id = (String) userMap.get("user_id");
        Date user_due_date = (Date) userMap.get("user_due_date");
        String password = (String) userMap.get("password");
        Date pass_update = (Date) userMap.get("pass_update");
        int login_miss_times = (Integer) userMap.get("login_miss_times");
        boolean lock_flg = (Boolean) userMap.get("lock_flg");
        boolean enabled_flg = (Boolean) userMap.get("enabled_flg");

        // 結果返却用のUserDetailsを生成
        return AppUserDetails.builder().user_id(user_id).user_due_date(user_due_date).password(password)
                .pass_update(pass_update).login_miss_times(login_miss_times).lock_flg(lock_flg).enabled_flg(enabled_flg)
                .authority(grantedAuthorityList).build();
    }
}
