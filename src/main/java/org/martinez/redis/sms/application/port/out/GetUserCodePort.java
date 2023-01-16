package org.martinez.redis.sms.application.port.out;

public interface GetUserCodePort {

  String getUserCode(String phone);
}
