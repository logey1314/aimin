package com.logey.aimin.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.logey.aimin.cache.config.AiminCacheProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class AiminCacheManager implements CacheManager {

    private  boolean dynamic ;
    private  boolean allowNullValues ;

    private AiminCacheProperties aimincacheProperties ;
    private L2Cache l2Cache;

    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>(16);

    public AiminCacheManager( AiminCacheProperties aimincacheProperties, L2Cache l2Cache) {
        this.dynamic  = aimincacheProperties.isDynamic() ;
        this.allowNullValues = aimincacheProperties.isAllowNullValues() ;
        this.aimincacheProperties = aimincacheProperties ;
        this.l2Cache = l2Cache ;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = this.cacheMap.get(name);
        if (cache == null && this.dynamic) {
            cache = this.cacheMap.computeIfAbsent(name, this::createAiminCache);
        }
        return cache;
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheMap.keySet());
    }

    private Cache createAiminCache(String name) {
        com.github.benmanes.caffeine.cache.Cache<Object, Object> l1cache = Caffeine.newBuilder().
                maximumSize(aimincacheProperties.getL1CacheSize()).
                expireAfterWrite(aimincacheProperties.getL1CacheExpire(), aimincacheProperties.getCacheTimeUnit()).build();
        //L2Cache l2Cache = new L2Cache(redisTemplate,aimincacheProperties);
        return new AiminCache(name,allowNullValues,l1cache,l2Cache);
    }

}
