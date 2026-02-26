package com.nipaaaa.gacha_system.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBannerStateRepository extends JpaRepository<UserBannerState, Long> {
  Optional<UserBannerState> findByUserIdAndBannerId(Long userId, Long bannerId);
}
