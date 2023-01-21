package org.martinez.redis.user.adapter.out.persistence;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.martinez.redis.common.annotation.PersistenceAdapter;
import org.martinez.redis.user.application.port.out.GetUserPort;
import org.martinez.redis.user.application.port.out.SaveUserPort;
import org.martinez.redis.user.domain.User;
import org.martinez.redis.user.domain.User.UserId;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements GetUserPort, SaveUserPort {

  public static final int USER_TOKEN_TTL = 30;
  private final UserRepository userRepository;
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public User getUser(String phone) {
    Optional<UserJpaEntity> userJpaEntityOpt = userRepository.findByPhone(phone);
    return userJpaEntityOpt.map(userJpaEntity ->
            User.withId(new UserId(userJpaEntity.getId()), userJpaEntity.getPhone()))
        .orElse(null);
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
    stringRedisTemplate.opsForHash().put(token, "id", String.valueOf(user.getId().getValue()));
    stringRedisTemplate.expire(token, USER_TOKEN_TTL, TimeUnit.MINUTES);
  }

}
