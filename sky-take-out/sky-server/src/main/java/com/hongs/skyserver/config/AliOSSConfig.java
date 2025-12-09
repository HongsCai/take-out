package com.hongs.skyserver.config;

import com.hongs.skycommon.properties.AliOSSProperties;
import com.hongs.skycommon.utils.AliOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliOSSUtil配置类
 */
@Configuration
@Slf4j
public class AliOSSConfig {
    @Bean
    @ConditionalOnMissingBean
    public AliOSSUtil aliOSSUtil(AliOSSProperties aliOSSProperties) {
        log.info("开始创建AliOSSUtil对象: {}", aliOSSProperties);
        return new AliOSSUtil(aliOSSProperties.getEndpoint(),
                aliOSSProperties.getAccessKeyId(),
                aliOSSProperties.getAccessKeySecret(),
                aliOSSProperties.getBucketName(),
                aliOSSProperties.getRegion());
    }
}
