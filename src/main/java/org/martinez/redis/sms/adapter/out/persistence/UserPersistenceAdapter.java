package org.martinez.redis.sms.adapter.out.persistence;

import org.martinez.redis.sms.application.port.out.GetUserPort;
import org.martinez.redis.sms.domain.User;

public class UserPersistenceAdapter implements GetUserPort {

  @Override
  public User getUser(String phone) {
    return null;
  }
}
