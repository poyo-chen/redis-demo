package org.martinez.redis.user.application.port.out;

import org.martinez.redis.user.domain.User;

public interface SaveUserPort {

  void saveUser(User user);

  void saveUserToken(String token, User user);
}
