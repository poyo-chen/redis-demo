package org.martinez.redis.user.application.port.out;

import org.martinez.redis.user.domain.User;

public interface GetUserPort {

  User getUser(String phone);
}
