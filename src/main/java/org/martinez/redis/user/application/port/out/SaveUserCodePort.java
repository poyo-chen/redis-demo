package org.martinez.redis.user.application.port.out;

public interface SaveUserCodePort {

  void saveUserCode(String phone,String code);
}
