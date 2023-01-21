package org.martinez.redis.user.adapter.interceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.martinez.redis.user.domain.User;
import org.martinez.redis.user.domain.User.UserId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Slf4j
public class RefreshTokenInterceptor implements
    HandlerInterceptor {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    // 取得前端token
    String token = request.getHeader("authorization");
    String key = "login:token" + token;
    if (token == null || token.isBlank()) {
      return true;
    }

    Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
    //判斷用戶存在
    if (userMap.isEmpty()) {
      return true;
    }

    //轉換User、存入ThreadLocal TODO
    User user = User.withId(
        new UserId(Long.getLong(userMap.get("id").toString())),
        (String) userMap.get("phone"));
    User.saveUser(user);
    //刷新有效期限
    return Boolean.TRUE.equals(stringRedisTemplate.expire(key, 30, TimeUnit.MINUTES));
  }
}
