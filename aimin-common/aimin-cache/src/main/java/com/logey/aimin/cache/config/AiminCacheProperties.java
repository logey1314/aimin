package com.logey.aimin.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@Data
@ConfigurationProperties(prefix = "aimin.cache")
public class AiminCacheProperties {

    private boolean dynamic = true;
    private boolean allowNullValues = true;
    private int l1CacheSize = 1000;
    private int l1CacheExpire = 120;
    private int l2CacheExpire = 120;
    private String redisChannel = "cache:invalidation";
    private TimeUnit cacheTimeUnit = TimeUnit.SECONDS;
}