package org.martinez.redis.shop.application.port.in;

import org.martinez.redis.common.Result;
import org.martinez.redis.shop.domain.Shop;

public interface ShopAdjustUseCase {

  Result update(Shop shop);
}
