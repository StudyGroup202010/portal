package com.portal.z.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    // フォームとメッセージの紐付け
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();

        //メッセージのプロパティファイル名（デフォルト）を指定します
        //下記ではmessages.propertiesファイルがセットされます
        bean.setBasename("classpath:messages");

        //メッセージプロパティの文字コードを指定します
        bean.setDefaultEncoding("UTF-8");

        return bean;
    }

    //　フォームとメッセージの紐付け
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());

        return localValidatorFactoryBean;
    }
}
