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

    /**
     * 権限を取得
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    /**
     * パスワードを取得
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * ユーザーIDを取得
     */
    @Override
    public String getUsername() {
        return this.user_id;
    }

    /**
     * アカウントの有効期限チェック<BR>
     * ユーザー有効期限が、現在日付よりも後かどうかをチェック<BR>
     * true: 有効（現在日付よりも後）<BR>
     * false:無効（現在日付よりも前）
     */
    @Override
    public boolean isAccountNonExpired() {
        if (this.user_due_date.after(new Date())) {
            // 現在日付よりも後
            return true;
        } else {
            // 現在日付よりも前
            return false;
        }
    }

    /**
     * アカウントのロックチェック<BR>
     * true: ロックされていない<BR>
     * false:ロックされている<BR>
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
     * パスワードの有効期限チェック<BR>
     * パスワードの有効期限が切れていたら別で再設定をするので、ここはtrueを返す<BR>
     * true:有効<BR>
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * アカウントの有効・無効チェック<BR>
     * true:有効<BR>
     * false:無効<BR>
     */
    @Override
    public boolean isEnabled() {
        return this.enabled_flg;
    }
}
