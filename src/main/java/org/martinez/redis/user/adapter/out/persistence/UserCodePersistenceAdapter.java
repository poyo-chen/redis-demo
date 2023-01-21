package org.martinez.redis.user.adapter.out.persistence;


import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.martinez.redis.common.annotation.PersistenceAdapter;
import org.martinez.redis.user.application.port.out.GetUserCodePort;
import org.martinez.redis.user.application.port.out.SaveUserCodePort;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
@PersistenceAdapter
class UserCodePersistenceAdapter implements SaveUserCodePort, GetUserCodePort {

  public static final int USER_CODE_TTL = 2;
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public void saveUserCode(String phone, String code) {
    stringRedisTemplate.opsForValue().set(phone, code, USER_CODE_TTL, TimeUnit.MINUTES);
  }

  @Override
  public String getUserCode(String phone) {
    return stringRedisTemplate.opsForValue().get(phone);
  }
}
