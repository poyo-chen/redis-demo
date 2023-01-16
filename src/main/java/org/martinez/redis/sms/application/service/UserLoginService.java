package org.martinez.redis.sms.application.service;

import lombok.RequiredArgsConstructor;
import org.martinez.redis.common.Result;
import org.martinez.redis.common.annotation.UseCase;
import org.martinez.redis.sms.application.port.in.LoginCommand;
import org.martinez.redis.sms.application.port.in.UserLoginUseCase;
import org.martinez.redis.sms.application.port.out.GetUserCodePort;
import org.martinez.redis.sms.application.port.out.GetUserPort;
import org.martinez.redis.sms.domain.User;

@RequiredArgsConstructor
@UseCase
public class UserLoginService implements UserLoginUseCase {

  private final GetUserCodePort getUserCodePort;
  private final GetUserPort getUserPort;

  public static final String LOGIN_CODE = "login:code:";

  @Override
  public Result login(LoginCommand loginCommand) {
    //1.檢查手機號碼
    if (checkPhoneNumber(loginCommand.getPhone())) {
      //2.若檢查失敗返,回錯誤訊息
      return Result.fail("手機格式錯誤");
    }
    //3.讀取 redis 中驗證碼，並檢查
    String cacheCode = getUserCodePort.getUserCode(LOGIN_CODE + loginCommand.getPhone());
    if (cacheCode == null || !cacheCode.equals(loginCommand.getCode())) {
      return Result.fail("驗證碼不一致");
    }
    //4.檢查一致則根據手機查詢用戶資訊
    User user = getUserPort.getUser(loginCommand.getPhone());

    return Result.ok(user);
  }

  private boolean checkPhoneNumber(String phone) {
    return phone.length() == 10;
  }
}
