package org.martinez.redis.sms.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.martinez.redis.common.Result;
import org.martinez.redis.sms.application.port.in.LoginCommand;
import org.martinez.redis.sms.application.port.in.UserLoginUseCase;
import org.martinez.redis.sms.application.port.in.UserSendCodeUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserSendCodeUseCase userSendCodeUsecase;

  private final UserLoginUseCase userLoginUseCase;

  /**
   * 發送手機驗證碼.
   *
   * @param phone 手機號碼
   * @return
   */
  @PostMapping("/code")
  public Result sendCode(@RequestParam("phone") String phone) {
    return userSendCodeUsecase.sendCode(phone);
  }

  /**
   * 登入.
   *
   * @param phone 手機號碼
   * @return
   */
  @PostMapping("/login")
  public Result login(@RequestParam("phone") String phone, @RequestParam("code") String code) {
    LoginCommand loginCommand = new LoginCommand(phone, code);
    return userLoginUseCase.login(loginCommand);
  }
}
