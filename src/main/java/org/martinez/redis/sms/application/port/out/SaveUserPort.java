package org.martinez.redis.sms.application.port.out;

import org.martinez.redis.sms.domain.User;

public interface SaveUserPort {

  void saveUser(User user);
}
