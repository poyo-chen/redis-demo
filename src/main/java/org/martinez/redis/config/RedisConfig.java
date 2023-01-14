package org.martinez.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    //創 redisTemplate
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //連接 factory
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    //創 Json 序列化工具
    GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    //設置 key 序列化
    redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
    //設置 value 序列化
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    // return
    return redisTemplate;
  }
}
