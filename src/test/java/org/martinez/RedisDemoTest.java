package org.martinez;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  void testString() {
    //插入一個 string 數據
    redisTemplate.opsForValue().set("Hello","World");
    //讀取 string 數據
    Object o = redisTemplate.opsForValue().get("Hello");
    System.out.println("o = " + o);

  }

}