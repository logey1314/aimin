package com.logey.aimin.cache;


import com.github.benmanes.caffeine.cache.Cache;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.concurrent.Callable;

@Slf4j
@Getter
public class AiminCache extends AbstractValueAdaptingCache {

    private  String cachename;

    private Cache<Object,Object> l1Cache;
    private L2Cache l2Cache;


    public AiminCache(String cachename,boolean allowNullValues,Cache<Object,Object> l1Cache,L2Cache l2Cache) {
        super(allowNullValues);
        this.cachename = cachename;
        this.l1Cache = l1Cache;
        this.l2Cache = l2Cache;
    }

    @Override
    protected Object lookup(Object key) {
        Object value = l1Cache.getIfPresent(key);
        if(value != null){
            return value;
        }
        value = l2Cache.get(getKey(key));
        l1Cache.put(key,toStoreValue(value));
        return value;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        log.info("————————写入缓存——————————");
        l2Cache.set(getKey(key),toStoreValue(value));
        l1Cache.put(key,toStoreValue(value));
        push(key);
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }

    public  String getKey(Object key){
        return  cachename+":"+key;
    }

    private void  push(Object key){
        l2Cache.push(getKey(key));
    }
}
