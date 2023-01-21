package org.martinez.redis.shop.application.port.in;

import org.martinez.redis.shop.domain.Shop;

public interface ShopCreateUseCase {

  void save(Shop shop);
}
