package org.martinez.redis.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result {

  public static final String SUCCESS = "操作成功。";
  public static final String FAILURE = "操作失败！";

  private boolean resultSuccess;
  private String msg;
  private Object data;

  public static Result ok() {
    return Result.ok(SUCCESS);
  }

  public static Result ok(String msg) {
    return Result.ok(msg, null);
  }

  public static Result ok(Object data) {
    return Result.ok(SUCCESS, data);
  }

  public static Result ok(String msg, Object data) {
    Result result = new Result();
    result.setResultSuccess(true);
    result.setMsg(msg);
    result.setData(data);
    return result;
  }

  public static Result fail() {
    return Result.fail(FAILURE);
  }

  public static Result fail(String msg) {
    return Result.fail(msg, null);
  }

  public static Result fail(Object data) {
    return Result.fail(FAILURE, data);
  }

  public static Result fail(String msg, Object data) {
    Result result = new Result();
    result.setResultSuccess(false);
    result.setMsg(msg);
    result.setData(data);
    return result;
  }
}
