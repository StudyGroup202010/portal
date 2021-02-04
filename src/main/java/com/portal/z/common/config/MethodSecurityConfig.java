package com.portal.z.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 認可設定<BR>
 * 
 * PreAuthorizeアノテーションを使用可能にする(prePostEnabled)<BR>
 * securedアノテーションを使用可能にする(securedEnabled)<BR>
 * RoleAlowedアノテーションを使用可能にする(jsr250Enabled)
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true
        , securedEnabled = true
        , jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
