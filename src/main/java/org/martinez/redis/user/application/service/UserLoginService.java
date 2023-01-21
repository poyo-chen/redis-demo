package org.martinez.redis.user.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.martinez.redis.common.Result;
import org.martinez.redis.common.annotation.UseCase;
import org.martinez.redis.user.application.port.in.LoginCommand;
import org.martinez.redis.user.application.port.in.UserLoginUseCase;
import org.martinez.redis.user.application.port.out.GetUserCodePort;
import org.martinez.redis.user.application.port.out.GetUserPort;
import org.martinez.redis.user.application.port.out.SaveUserPort;
import org.martinez.redis.user.domain.User;

@RequiredArgsConstructor
@UseCase
@Slf4j
public class UserLoginService implements UserLoginUseCase {

  public static final String LOGIN_TOKEN = "login:token";
  private final GetUserCodePort getUserCodePort;
  private final GetUserPort getUserPort;
  private final SaveUserPort saveUserPort;

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
    if (user == null) {
      //5 不存在，創建新用戶 TODO
      user = createUserWithPhone(loginCommand.getPhone());
    }
    //6 隨機生成 Token
    String token = UUID.randomUUID().toString();
    saveUserPort.saveUserToken(LOGIN_TOKEN + token, user);

    return Result.ok(token);
  }

  private User createUserWithPhone(String phone) {
    saveUserPort.saveUser(User.withoutId(phone));
    log.info("create new user: {}", phone);
    return getUserPort.getUser(phone);
  }

  private boolean checkPhoneNumber(String phone) {
    return phone.length() != 10;
  }
}
