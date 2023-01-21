package org.martinez.redis.user.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.martinez.redis.common.Result;
import org.martinez.redis.common.annotation.UseCase;
import org.martinez.redis.user.application.port.in.UserSendCodeUseCase;
import org.martinez.redis.user.application.port.out.SaveUserCodePort;

@Slf4j
@RequiredArgsConstructor
@UseCase
public class UserSendCodeService implements UserSendCodeUseCase {

  public static final String LOGIN_CODE = "login:code:";
  private final SaveUserCodePort saveUserCodePort;

  @Override
  public Result sendCode(String phone) {
    //1.檢查手機號碼
    if (checkPhoneNumber(phone)) {
      //2.若檢查失敗返,回錯誤訊息
      return Result.fail("手機格式錯誤");
    }
    //3.檢查通過,產生驗證碼 TODO
    String code = String.valueOf(123456);
    //4.將驗證碼存入 redis
    saveUserCodePort.saveUserCode(LOGIN_CODE + phone, code);
    //5.發送驗證碼 TODO
    log.info("Code send success! code: {}", code);
    //6.返回成功
    return Result.ok();
  }

  private boolean checkPhoneNumber(String phone) {
    return phone.length() != 10;
  }
}
