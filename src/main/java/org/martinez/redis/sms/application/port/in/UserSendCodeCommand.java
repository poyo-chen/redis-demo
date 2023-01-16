package org.martinez.redis.sms.application.port.in;

import javax.servlet.http.HttpSession;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class UserSendCodeCommand {

  private final String phone;
  private final HttpSession session;
}
