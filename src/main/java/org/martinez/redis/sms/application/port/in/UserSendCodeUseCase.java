package org.martinez.redis.sms.application.port.in;

import org.martinez.redis.common.Result;

public interface UserSendCodeUseCase {
  Result sendCode(String phone);
}
