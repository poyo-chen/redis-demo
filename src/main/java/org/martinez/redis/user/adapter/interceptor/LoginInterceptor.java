package org.martinez.redis.user.adapter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.martinez.redis.user.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Slf4j
public class LoginInterceptor implements
    HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    if (User.getUser() == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    return true;
  }
}
