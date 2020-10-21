package com.portal.z.login.domain.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// =========================
    // Springで必要なフィールド
    // =========================
    private String user_id;       //ユーザーID
    private Date user_due_date;   //ユーザー有効期限
    private String password;      //パスワード
    private Date pass_update;     //パスワード有効期限
    private int login_miss_times; //ログイン失敗回数
    private boolean lock_flg;     //ロック状態
    private boolean enabled_flg;  //有効フラグ

    //権限のCollection
    private Collection<? extends GrantedAuthority> authority;

    // =========================
    // 独自のフィールド
    // =========================
    //private String tenant_id;     //テナントＩＤ
    //private String user_name;     //ユーザー名

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.user_id;
    }

    /** アカウントの有効期限チェック
     * true: 有効
     * false:無効
     */
    @Override
    public boolean isAccountNonExpired() {
        //ユーザー有効期限が、現在日付よりも後かどうかをチェック
        if(this.user_due_date.after(new Date())) {
            //現在日付よりも後なら有効
            return true;
        } else {
            //現在日付よりも前なら無効
            return false;
        }
    }

    /** アカウントのロックチェック
     * true: アンロック
     * false: ロック
     */
    @Override
    public boolean isAccountNonLocked() {
    	if (this.isLock_flg()) {
    		//ロックされている
    		return false;
    	} else {
    		//ロックされていない
            return true;
    	}
    }

    /** パスワードの有効期限チェック
     * true:有効
     * false:無効
     */
//
//　パスワードの有効期限が切れていたら再設定をするので、この部分はコメント化    
//
    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
//        //パスワード有効期限が、現在日付よりも後かどうかをチェック
//        if(this.pass_update.after(new Date())) {
//            //現在日付よりも後なら有効
//            return true;
//        } else {
//            //現在日付よりも前なら無効
//            return false;
//        }
    }

    /** アカウントの有効・無効チェック
     * true:有効
     * false:無効
     */
    @Override
    public boolean isEnabled() {
        return this.enabled_flg;
    }
}
