package com.logey.aimin.cache;

import com.logey.aimin.cache.config.AiminCacheProperties;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.concurrent.TimeUnit;

public class L2Cache {



    private final RedisTemplate<String, Object> redisTemplate;

    private final AiminCacheProperties aiminCacheProperties;

    public L2Cache(RedisTemplate<String, Object> redisTemplate, AiminCacheProperties aiminCacheProperties) {
        this.redisTemplate = redisTemplate;
        this.aiminCacheProperties = aiminCacheProperties;
    }

    public Object  get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value,aiminCacheProperties.getL2CacheExpire());
    }

    public void  push(String key){
        redisTemplate.convertAndSend(aiminCacheProperties.getRedisChannel(),key);
    }

}
