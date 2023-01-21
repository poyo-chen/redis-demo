package org.martinez.redis.shop.application.port.in;

import org.martinez.redis.common.Result;

public interface ShopFoundUseCase {

  Result findShopByType(Integer typeId, Integer current, Double x, Double y);

  Result findById();
}
