package org.martinez;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.martinez.redis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class StringRedisDemoTest {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  private static final ObjectMapper objectMapper=new ObjectMapper();

  @Test
  void saveUser() throws JsonProcessingException {
    //準備對象
    User user = new User("Json", 22);
    //手動序列化
    String json = objectMapper.writeValueAsString(user);
    //用 String 寫入 redis
    stringRedisTemplate.opsForValue().set("user:100",json);
    //讀取數據
    String obj = stringRedisTemplate.opsForValue().get("user:100");
    //手動反序列化
    User user1 = objectMapper.readValue(obj, User.class);
    System.out.println("user1 = " + user1);
  }
}
