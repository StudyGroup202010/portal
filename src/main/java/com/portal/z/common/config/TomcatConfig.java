package com.portal.z.common.config;

import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.jdbc.config.annotation.RdsInstanceConfigurer;
import io.awspring.cloud.jdbc.datasource.DataSourceFactory;
import io.awspring.cloud.jdbc.datasource.TomcatJdbcDataSourceFactory;

/**
 * Tomcat用の設定<BR>
 *
 */
@Configuration
public class TomcatConfig {

    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return server -> Optional.ofNullable(server)
                .ifPresent(s -> s.addAdditionalTomcatConnectors(redirectConnector()));
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(8009);
        connector.setRedirectPort(8443);
        connector.setSecure(false);
        connector.setAllowTrace(false);

        AbstractAjpProtocol<?> protocol = (AbstractAjpProtocol<?>) connector.getProtocolHandler();
        connector.setSecure(false);
        protocol.setSecretRequired(false);

        return connector;
    }
    
    @Bean
    RdsInstanceConfigurer instanceConfigurer() {
        return new RdsInstanceConfigurer() {
            @Override
            public DataSourceFactory getDataSourceFactory() {
                TomcatJdbcDataSourceFactory dataSourceFactory = new TomcatJdbcDataSourceFactory();
                //https://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html#Configuring_JDBC_interceptors
                dataSourceFactory.setTestOnBorrow(true);
                dataSourceFactory.setTestWhileIdle(true);
                dataSourceFactory.setValidationQuery("SELECT 1");
                dataSourceFactory.setTimeBetweenEvictionRunsMillis(60000);
                return dataSourceFactory;
            }
        };
    }
    
}
