package com.kroger.ngpos.instore.accounting.distributed.locking.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@ConditionalOnBooleanProperty(value = "app.schedule.distributed-lock.enable")
public class DistributedCacheConfiguration {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60));
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

}
