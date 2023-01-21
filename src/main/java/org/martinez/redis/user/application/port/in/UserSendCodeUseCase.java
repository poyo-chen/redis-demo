package org.martinez.redis.user.application.port.in;

import org.martinez.redis.common.Result;

public interface UserSendCodeUseCase {
  Result sendCode(String phone);
}
