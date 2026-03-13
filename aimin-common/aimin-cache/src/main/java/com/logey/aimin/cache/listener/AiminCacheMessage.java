package com.logey.aimin.cache.listener;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AiminCacheMessage implements Serializable {

    private String cacheName;

    private Object key;

    private long pid;

    public AiminCacheMessage(String cacheName, Object key, long pid) {
        this.cacheName = cacheName;
        this.key = key;
        this.pid = pid;
    }
}
