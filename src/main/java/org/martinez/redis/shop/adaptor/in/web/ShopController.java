package org.martinez.redis.shop.adaptor.in.web;

import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.martinez.redis.common.Result;
import org.martinez.redis.shop.application.port.in.ShopAdjustUseCase;
import org.martinez.redis.shop.application.port.in.ShopCreateUseCase;
import org.martinez.redis.shop.application.port.in.ShopFoundUseCase;
import org.martinez.redis.shop.domain.Shop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

  private final ShopCreateUseCase shopCreateUseCase;
  private final ShopAdjustUseCase shopAdjustUseCase;

  private final ShopFoundUseCase shopFoundUseCase;

  /**
   * 根據id查詢商店信息
   * @param id 商店id
   * @return 商店詳情數據
   */
  @GetMapping("/{id}")
  public Result getShopById(@PathParam("{id}")Long id){
    return shopFoundUseCase.findById();
  }

  /**
   * 新增商店信息
   * @param shop 商店數據
   * @return 商店id
   */
  @PostMapping
  public Result saveShop(@RequestBody Shop shop) {
    // 寫入數據庫
    shopCreateUseCase.save(shop);
    // 返回店鋪id
    return Result.ok(shop.getId());
  }

  /**
   * 更新商店信息
   * @param shop 商店數據
   * @return 無
   */
  @PutMapping
  public Result updateShop(@RequestBody Shop shop) {
    // 寫入數據庫
    return shopAdjustUseCase.update(shop);
  }

  /**
   * 根據商店類型分頁查詢商店信息
   * @param typeId 商店類型
   * @param current 頁碼
   * @return 商店列表
   */
  @GetMapping("/of/type")
  public Result queryShopByType(
      @RequestParam("typeId") Integer typeId,
      @RequestParam(value = "current", defaultValue = "1") Integer current,
      @RequestParam(value = "x", required = false) Double x,
      @RequestParam(value = "y", required = false) Double y
  ) {
    return shopFoundUseCase.findShopByType(typeId, current, x, y);
  }

  /**
   * 根據商店名稱關鍵字分頁查詢商店信息
   * @param name 商店名稱關鍵字
   * @param current 頁碼
   * @return 商店列表
   */
  @GetMapping("/of/name")
  public Result queryShopByName(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "current", defaultValue = "1") Integer current
  ) {
    // 根據類型分頁查詢
//    Page<Shop> page = shopService.query()
//        .like(StrUtil.isNotBlank(name), "name", name)
//        .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));page.getRecords()
    // 返回數據
    return Result.ok();
  }
}
