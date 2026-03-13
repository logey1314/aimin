package com.logey.aimin.cache;

import com.logey.aimin.cache.config.AiminCacheProperties;
import com.logey.aimin.cache.listener.AiminCacheListener;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Objects;

@AutoConfiguration
@EnableConfigurationProperties(AiminCacheProperties.class)
public class AiminCacheAutoConfiguration {
    @Bean
    public CacheManager cacheManager( AiminCacheProperties aiminCacheProperties,L2Cache l2Cache) {
        return new AiminCacheManager(aiminCacheProperties,l2Cache);
    }

    @Bean
    public L2Cache l2Cache(RedisTemplate<String, Object> redisTemplate, AiminCacheProperties aiminCacheProperties) {
        return new L2Cache(redisTemplate, aiminCacheProperties);
    }


    @Bean
    public RedisMessageListenerContainer container(RedisTemplate<String, Object> redisTemplate,
                                                   AiminCacheProperties aiminCacheProperties,
                                                   CacheManager cacheManager) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        AiminCacheListener aiminCacheListener = new AiminCacheListener(redisTemplate, (AiminCacheManager) cacheManager);
        String redisChannel = aiminCacheProperties.getRedisChannel();
        redisMessageListenerContainer.addMessageListener(aiminCacheListener,new ChannelTopic(redisChannel));
        redisMessageListenerContainer.setConnectionFactory(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        return redisMessageListenerContainer;
    }



}