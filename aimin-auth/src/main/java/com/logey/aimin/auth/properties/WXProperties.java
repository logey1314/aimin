package com.logey.aimin.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "wx")
@Component
public class WXProperties {
    private String appID;
    private String appSecret;
}
