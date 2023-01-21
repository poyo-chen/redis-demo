package org.martinez.redis.user.application.port.in;

import org.martinez.redis.common.Result;

public interface UserLoginUseCase {

  Result login(LoginCommand loginCommand);
}
