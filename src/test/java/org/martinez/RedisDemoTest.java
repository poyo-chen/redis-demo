package org.martinez;

import org.junit.jupiter.api.Test;
import org.martinez.redis.pojo.User;
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

  @Test
  void testSaveUser(){
    //寫入數據
    redisTemplate.opsForValue().set("user:100",new User("Jack",21));
    //讀取數據
    User o = (User) redisTemplate.opsForValue().get("user:100");
    System.out.println("o = " + o);
  }
}