package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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

        //静的リソースへのアクセスには、セキュリティを適用しない
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // ログイン不要ページの設定
        http
            .authorizeRequests()
                .antMatchers("/webjars/**").permitAll() //webjarsへアクセス許可
                .antMatchers("/css/**").permitAll() //cssへアクセス許可
                .antMatchers("/login").permitAll() //ログインページは直リンクOK
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //アドミンユーザーに許可
                .anyRequest().authenticated(); //それ以外は直リンク禁止

        //ログイン処理
        http
            .formLogin()
                .loginProcessingUrl("/login") //ログイン処理のパス
                .loginPage("/login") //ログインページの指定
                .failureUrl("/login") //ログイン失敗時の遷移先
                .usernameParameter("user_id") //ログインページのユーザーID
                .passwordParameter("password") //ログインページのパスワード
                .defaultSuccessUrl("/home", true); //ログイン成功後の遷移先

        //ログアウト処理
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
                .logoutUrl("/logout") //ログアウトのURL
                .logoutSuccessUrl("/login"); //ログアウト成功後のURL

    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // ログイン処理時のユーザー情報を、DBから取得する
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }
}
