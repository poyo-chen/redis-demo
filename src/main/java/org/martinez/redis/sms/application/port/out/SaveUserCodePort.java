package org.martinez.redis.sms.application.port.out;

public interface SaveUserCodePort {

  void saveUserCode(String phone,String code);
}
