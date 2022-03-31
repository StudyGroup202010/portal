package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * セキュリティ設定
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // ログインに成功したとき
    @Autowired
    @Qualifier("SuccessHandler")
    AuthenticationSuccessHandler successHandler;

    // ログイン処理時のユーザ情報を取得する
    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * パスワードを暗号化する
     * 
     * @return 暗号化したパスワード
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 静的リソースを除外する。<BR>
     * 
     * 静的リソースには誰でもアクセスできて良いので、セキュリティを適用しない。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
    }

    /**
     * 直リンク（ログイン無しのアクセス）の設定<BR>
     * 
     * ・許可したURL以外は直接アクセスすることを禁止。<BR>
     * ・レスポンスヘッダーの設定<BR>
     * ・ログイン処理<BR>
     * ・セッション管理（セッションエラー後の遷移先）<BR>
     * ・ログアウト処理<BR>
     * ・CSRF対策は有効（デフォルト）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
                .antMatchers("/css/**").permitAll() // cssへアクセス許可
                .antMatchers("/login").permitAll() // ログインページは直リンクOK
                .antMatchers("/pwreissue").permitAll() // パスワード再設定ページは直リンクOK
                .antMatchers("/resetpassword").permitAll() // パスワード再発行ページは直リンクOK
                .antMatchers("/api/**").permitAll() // RestAPIは直リンクOK
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // 指定ロール名に許可(ロールマスタのロール名）
                .antMatchers("/error/session").permitAll() // セッションエラー
                .anyRequest().authenticated(); // それ以外は直リンク禁止

        // レスポンスヘッダーの設定
        // セキュリティを高めるために自分自身のサーバのスクリプトだけが実行できるように制限する
        // コロンで区切って記述する。参考：https://qiita.com/tearoom6/items/30e3aacaa432860d4b36
        // nonce-c3R1ZHlwb3J0YWwyMDIwをつけるとインラインのJavaScriptが実行できる。
        // 天気予報：https://weather.tsukumijima.net
        // 天気予報：https://www.jma.go.jp
        // 郵便番号：https://zipcloud.ibsnet.co.jp
        // http.headers().contentSecurityPolicy("default-src 'self'");
        http.headers().contentSecurityPolicy(
                  "default-src 'self' ;"
                + "script-src-elem 'self' 'nonce-c3R1ZHlwb3J0YWwyMDIw' https://zipcloud.ibsnet.co.jp ;"
                + "script-src 'self' 'nonce-c3R1ZHlwb3J0YWwyMDIw' ;"
                + "img-src 'self' data: https://www.jma.go.jp ;"
                + "connect-src https://weather.tsukumijima.net");

        // ログイン処理
        http.formLogin().loginProcessingUrl("/login") // ログイン処理のパス（login.htmlの（action="/login"）と一致させること
                .loginPage("/login") // ログインページの指定（ログインページを自作した場合は必ず設定すること）
                // loginコントローラの「@GetMapping("/login")」と合わせること
                .failureUrl("/login") // ログイン失敗時の遷移先
                .usernameParameter("user_id") // ログインページのユーザーID
                .passwordParameter("password") // ログインページのパスワード
                .defaultSuccessUrl("/home", true) // ログイン成功後の遷移先
                .successHandler(successHandler); // ログイン成功時にsuccessHandlerを使うように設定

        // セッション管理
        http.sessionManagement().invalidSessionUrl("/error/session"); // セッションエラー後の遷移先

        // ログアウト処理
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
                .logoutUrl("/logout") // ログアウトのURL
                .logoutSuccessUrl("/login") // ログアウト成功後のURL
                .deleteCookies("JSESSIONID");// ログアウト時にクッキーを消す（これをしないとログアウト時にエラーとなる
    }

    /**
     * ログイン処理時のユーザー情報を、DBから取得する
     * 
     * 認証プロバイダの設定(setHideUserNotFoundExceptionsをfalseにするため)
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 認証プロバイダの設定(setHideUserNotFoundExceptionsをfalseにするため)
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 認証プロバイダの生成を行う。<BR>
     * 
     * 認証プロバイダに関する設定はここで行う。<BR>
     * ・setHideUserNotFoundExceptionsはfalseに設定
     * 
     * @return daoAuthenticationProvider 認証プロバイダ
     */
    protected AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(userDetailsService);
        impl.setPasswordEncoder(passwordEncoder()); // ログイン時のパスワードの復号
        impl.setHideUserNotFoundExceptions(false);
        return impl;
    }
}
