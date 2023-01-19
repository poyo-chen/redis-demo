package org.martinez.redis.sms.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity,Long> {

  Optional<UserJpaEntity> findByPhone(String phone);
}
