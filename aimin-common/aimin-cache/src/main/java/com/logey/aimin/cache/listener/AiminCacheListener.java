package com.logey.aimin.cache.listener;

import com.github.benmanes.caffeine.cache.Cache;
import com.logey.aimin.cache.AiminCache;
import com.logey.aimin.cache.AiminCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class AiminCacheListener implements MessageListener {
    private final RedisTemplate<String, Object> redisTemplate;

    private final AiminCacheManager cacheManager;

    public AiminCacheListener(RedisTemplate<String, Object> redisTemplate, AiminCacheManager cacheManager) {
        this.redisTemplate = redisTemplate;
        this.cacheManager = cacheManager;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        AiminCacheMessage aiminCacheMessage = (AiminCacheMessage)redisTemplate.getValueSerializer().deserialize(message.getBody());

        AiminCache cache = (AiminCache)cacheManager.getCache(aiminCacheMessage.getCacheName());
        Cache<Object, Object> l1Cache = cache.getL1Cache();
        long pid = ProcessHandle.current().pid();
        if(pid != aiminCacheMessage.getPid()){
            l1Cache.invalidate(aiminCacheMessage.getKey());
        }
    }
}
