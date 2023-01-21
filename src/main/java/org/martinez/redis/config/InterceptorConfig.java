package org.martinez.redis.config;

import lombok.RequiredArgsConstructor;
import org.martinez.redis.user.adapter.interceptor.LoginInterceptor;
import org.martinez.redis.user.adapter.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
        .order(0);
    registry.addInterceptor(new LoginInterceptor())
        .excludePathPatterns(
            "/user/login",
            "/user/code",
            "/shop/**"
        ).order(1);
  }

}
