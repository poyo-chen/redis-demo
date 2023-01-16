package org.martinez.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.martinez.redis.base.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class StringRedisDemoTest {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void saveUser() throws JsonProcessingException {
    //準備對象
    UserBase userBase = new UserBase("Json", 22);
    //手動序列化
    String json = objectMapper.writeValueAsString(userBase);
    //用 String 寫入 redis
    stringRedisTemplate.opsForValue().set("user:100", json);
    //讀取數據
    String obj = stringRedisTemplate.opsForValue().get("user:100");
    //手動反序列化
    UserBase userBase1 = objectMapper.readValue(obj, UserBase.class);
    System.out.println("user1 = " + userBase1);
    Assertions.assertEquals(json, obj);
  }

  @Test
  void testHash() {
    stringRedisTemplate.opsForHash().put("user:300", "name", "Lily");
    stringRedisTemplate.opsForHash().put("user:300", "age", "23");

    Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:300");
    System.out.println("entries = " + entries);
    Assertions.assertNotNull(entries);
  }
}
