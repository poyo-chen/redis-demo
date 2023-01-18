package org.martinez.redis.sms.adapter.interceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Slf4j
public class LoginInterceptor implements
    HandlerInterceptor {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("start1");
    // 取得前端token
    String token = request.getHeader("authorization");
    String key = "login:token" + token;
    if (token == null || token.isBlank()) {
      //不存在，攔截
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
    //判斷用戶存在
    if (userMap.isEmpty()) {
      //不存在，攔截
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    stringRedisTemplate.expire(key, 30, TimeUnit.MINUTES);

    return true;
  }
}
