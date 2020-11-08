package com.portal.z.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true  //PreAuthorizeアノテーションを使用可能にする
        ,securedEnabled = true  //securedアノテーションを使用可能にする
        ,jsr250Enabled  = true) //RoleAlowedアノテーションを使用可能にする
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

}
