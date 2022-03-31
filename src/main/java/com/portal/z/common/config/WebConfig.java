package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.portal.z.common.domain.util.Constants;

/**
 * Webの設定<BR>
 * 
 * ユーザＩＤをログに出力する（LoggingIntercepterの使用）<BR>
 * フォームとメッセージの紐付け
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ユーザＩＤをログに出力する
    @Autowired
    @Qualifier("LoggingIntercepter")
    HandlerInterceptor loggingInterceptor;

    /**
     * Integercepterを追加.
     */
    // ユーザＩＤをログに出力する
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

    /**
     * メッセージプロパティをセットする。
     * 
     * @return メッセージプロパティ
     */
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();

        // メッセージのプロパティファイル名（デフォルト）を指定します
        // 下記ではmessages.propertiesファイルがセットされます
        bean.setBasename("classpath:messages");

        // メッセージプロパティの文字コードを指定します
        bean.setDefaultEncoding("UTF-8");

        return bean;
    }

    /**
     * バリデーションエラーメッセージをValidationMessages.propertiesからmessages.propertiesに変更する。<BR>
     * メッセージは１箇所に集約した方がわかりやすいため。
     * 
     * @return localValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());

        return localValidatorFactoryBean;
    }

    /**
     * CORS設定<BR>
     * RestApi用にCORSを有効にします。
     *
     */
    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**")
                    //TODO 全オリジンを有効にしているので、号口環境が決定したら再設定すること
                    .allowedOrigins(Constants.CORS_ALLOWED_ORIGINS_1,"*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE");
        }
    }
}
