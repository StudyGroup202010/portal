package com.portal.z.common.domain.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザ情報
 *
 */
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
    /**
     * ユーザーID
     */
    private String user_id;
    /**
     * ユーザー有効期限
     */
    private Date user_due_date;
    /**
     * パスワード
     */
    private String password;
    /**
     * パスワード有効期限
     */
    private Date pass_update;
    /**
     * ログイン失敗回数
     */
    private int login_miss_times;
    /**
     * ロック状態
     */
    private boolean lock_flg;
    /**
     * 有効フラグ
     */
    private boolean enabled_flg;

    /**
     * 権限のCollection
     */
    private Collection<? extends GrantedAuthority> authority;

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

    /**
     * アカウントの有効期限チェック true: 有効 false:無効
     */
    @Override
    public boolean isAccountNonExpired() {
        // ユーザー有効期限が、現在日付よりも後かどうかをチェック
        if (this.user_due_date.after(new Date())) {
            // 現在日付よりも後なら有効
            return true;
        } else {
            // 現在日付よりも前なら無効
            return false;
        }
    }

    /**
     * アカウントのロックチェック true: アンロック false: ロック
     */
    @Override
    public boolean isAccountNonLocked() {
        if (this.isLock_flg()) {
            // ロックされている
            return false;
        } else {
            // ロックされていない
            return true;
        }
    }

    /**
     * パスワードの有効期限チェック true:有効 false:無効
     */
    //
    // パスワードの有効期限が切れていたら再設定をするので、この部分trueを返すだけとする
    //
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * アカウントの有効・無効チェック true:有効 false:無効
     */
    @Override
    public boolean isEnabled() {
        return this.enabled_flg;
    }
}
