package com.logey.aimin.generator.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mysql")  // ✓ 指定前缀
@Component
@Data
public class MysqlProperties {
    private String url;
    private String username;
    private String password;

}
