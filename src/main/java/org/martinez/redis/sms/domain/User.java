package org.martinez.redis.sms.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

  @Getter
  private final UserId id;
  @Getter
  private final String phone;

  public static User withoutId(String phone) {
    return new User(null, phone);
  }

  public static User withId(UserId userId, String phone) {
    return new User(userId, phone);
  }

  @Value
  public static class UserId {

    private final Long value;
  }

  public static void saveUser(User user) {
    userThreadLocal.set(user);
  }

  public static User getUser() {
    return userThreadLocal.get();
  }

  public static void removeUser() {
    userThreadLocal.remove();
  }
}
