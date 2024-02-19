package com.cryss.tipsandlearnings.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.cache.annotation.CachingConfigurer;

import java.time.Duration;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class RedisConfig implements CachingConfigurer {

    @Value ("${spring.data.redis.sentinel.nodes}")
    private String redisHost;


    @Value("${spring.data.redis.port}")
    private int redisPort;



    @Value("${spring.cache.redis.time-to-live}")
    private long redisTimeToLive;

    @Value("${spring.data.redis.timeout}")
    private Duration redisCommandTimeout;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);


        return new LettuceConnectionFactory(configuration);
    }


    @Bean
    public RedisCacheManager cacheManager() {
        ObjectMapper mapper = new ObjectMapper();


        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10), mapper).disableCachingNullValues();


        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("carts_items", myDefaultCacheConfig(Duration.ofMinutes(5), mapper))
                .withCacheConfiguration("carts_items", myDefaultCacheConfig(Duration.ofMinutes(1), mapper))
                .build();
    }


    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration, ObjectMapper mapper) {


        var myMapper = mapper.copy ()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule (
                        new Hibernate6Module ()
                                .enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING)
                                .enable(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS)
                ).activateDefaultTyping (
                        mapper.getPolymorphicTypeValidator (),
                        ObjectMapper.DefaultTyping.EVERYTHING,
                        JsonTypeInfo.As.PROPERTY
                );


        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer (myMapper)));
    }


    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.info("Failure getting from cache: " + cache.getName() + ", exception: " + exception.toString());
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.info("Failure putting into cache: " + cache.getName() + ", exception: " + exception.toString());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.info("Failure evicting from cache: " + cache.getName() + ", exception: " + exception.toString());
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.info("Failure clearing cache: " + cache.getName() + ", exception: " + exception.toString());
            }
        };
    }



}