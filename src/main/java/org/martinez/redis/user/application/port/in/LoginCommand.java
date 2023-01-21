package org.martinez.redis.user.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginCommand {

  String phone;
  String code;
}
