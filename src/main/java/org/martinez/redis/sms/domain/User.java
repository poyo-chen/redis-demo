package org.martinez.redis.sms.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  private final String phone;

  public static User withPhone(String phone) {
    return new User(phone);
  }
}
