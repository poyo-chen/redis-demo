package org.martinez.redis.sms.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  private final UserId id;
  private final String phone;

  public static User withoutId(String phone) {
    return new User(null, phone);
  }

  public static User withId(UserId userId, String phone) {
    return new User(userId, phone);
  }

  @Value
  public static class UserId {

    private Long value;
  }
}
