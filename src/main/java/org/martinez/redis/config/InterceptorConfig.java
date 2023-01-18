package org.martinez.redis.config;

import lombok.RequiredArgsConstructor;
import org.martinez.redis.sms.adapter.interceptor.LoginInterceptor;
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
    registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/user/login",
            "/user/code"
        );
  }

}
