package org.martinez.redis.sms.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoginCommand {

  private final String phone;
  private final String code;
}
