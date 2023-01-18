package org.martinez.redis.sms.adapter.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.martinez.redis.common.annotation.PersistenceAdapter;
import org.martinez.redis.sms.application.port.out.GetUserPort;
import org.martinez.redis.sms.application.port.out.SaveUserPort;
import org.martinez.redis.sms.domain.User;
import org.martinez.redis.sms.domain.User.UserId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements GetUserPort, SaveUserPort {

  private final UserRepository userRepository;
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public User getUser(String phone) {
    UserJpaEntity userJpaEntity = userRepository.findByPhone(phone);
    return User.withId(new UserId(userJpaEntity.getId()), userJpaEntity.getPhone());
  }

  @Override
  public void saveUser(User user) {
    UserJpaEntity userJpaEntity = new UserJpaEntity();
    userJpaEntity.setPhone(user.getPhone());
    userRepository.save(userJpaEntity);
  }

  @Override
  public void saveUserToken(String token, User user) {
    stringRedisTemplate.opsForHash().put(token, "phone", user.getPhone());
    stringRedisTemplate.expire(token, 30, TimeUnit.MINUTES);
  }

}
