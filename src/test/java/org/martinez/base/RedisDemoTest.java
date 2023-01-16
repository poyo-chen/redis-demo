package org.martinez.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.martinez.redis.base.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoTest {

  @Autowired
  private RedisTemplate<String,Object> redisTemplate;

  @Test
  void testString() {
    //插入一個 string 數據
    redisTemplate.opsForValue().set("Hello", "World");
    //讀取 string 數據
    Object o = redisTemplate.opsForValue().get("Hello");
    System.out.println("o = " + o);
    Assertions.assertEquals("World", o);
  }

  @Test
  void testSaveUser() {
    //寫入數據
    redisTemplate.opsForValue().set("user:100", new UserBase("Jack", 21));
    //讀取數據
    UserBase o = (UserBase) redisTemplate.opsForValue().get("user:100");
    System.out.println("o = " + o);
    Assertions.assertNotNull(o);
    Assertions.assertEquals("Jack", o.getName());
    Assertions.assertEquals(21, o.getAge());
  }


}