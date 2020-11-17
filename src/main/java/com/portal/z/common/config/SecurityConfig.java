package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //ログインに成功したとき
    @Autowired
    @Qualifier("SuccessHandler")
    AuthenticationSuccessHandler successHandler;

    //ログイン処理時のユーザ情報を取得する
    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    //パスワードの暗号化
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        //静的リソースへのアクセスには、誰でもアクセスできても良いので、セキュリティを適用しない。
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 直リンク（ログイン無しのアクセス）禁止の設定
        http
        .authorizeRequests()
        .antMatchers("/webjars/**").permitAll() //webjarsへアクセス許可
        .antMatchers("/css/**").permitAll() //cssへアクセス許可
        .antMatchers("/login").permitAll() //ログインページは直リンクOK
        .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //指定ロール名に許可(ロールマスタのロール名）
        .antMatchers("/error/session").permitAll() // セッションエラー
        .anyRequest().authenticated(); //それ以外は直リンク禁止

        // レスポンスヘッダーの設定
        //　セキュリティを高めるために自分自身のサーバのスクリプトだけが実行できるように制限する
        http
        .headers()
        .contentSecurityPolicy("default-src 'self'");

        //ログイン処理
        http
        .formLogin()
        .loginProcessingUrl("/login") //ログイン処理のパス（login.htmlの（action="/login"）と一致させること      
        .loginPage("/login") //ログインページの指定（ログインページを自作した場合は必ず設定すること）
        //loginコントローラの「@GetMapping("/login")」と合わせること
        .failureUrl("/login") //ログイン失敗時の遷移先
        .usernameParameter("user_id") //ログインページのユーザーID
        .passwordParameter("password") //ログインページのパスワード
        .defaultSuccessUrl("/home", true) //ログイン成功後の遷移先
        .successHandler(successHandler); //ログイン成功時にsuccessHandlerを使うように設定


        // セッション管理
        http.sessionManagement()
        .invalidSessionUrl("/error/session"); // セッションエラー後の遷移先

        //ログアウト処理
        http
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
        .logoutUrl("/logout")        //ログアウトのURL
        .logoutSuccessUrl("/login")  //ログアウト成功後のURL
        .deleteCookies("JSESSIONID");//ログアウト時にクッキーを消す（これをしないとログアウト時にエラーとなる

        // CSRF対策はデフォルトで有効
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // ログイン処理時のユーザー情報を、DBから取得する
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder()); //ログイン時のパスワードの復号
    }
}
