package com.cryss.tipsandlearnings.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Value ("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

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

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();

        var myMapper = mapper.copy ()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule (
                      new Hibernate6Module()
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

}