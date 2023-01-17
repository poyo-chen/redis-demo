package org.martinez.redis.sms.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity,Long> {

  UserJpaEntity findByPhone(String phone);
}
