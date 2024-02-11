package com.example.big_event.config;

import com.example.big_event.properties.AliOssProperties;

import com.example.big_event.utils.AliOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建AliOssUntil对象
 */
@Configuration
public class AliOSSConfiguration {

    @Bean
    @ConditionalOnMissingBean  //保证容器中只有这一个bean对象
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getBucketName());
    }
}
