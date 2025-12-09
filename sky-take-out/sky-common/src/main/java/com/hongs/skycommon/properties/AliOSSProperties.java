package com.hongs.skycommon.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS属性配置
 */
@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOSSProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String region;
}
