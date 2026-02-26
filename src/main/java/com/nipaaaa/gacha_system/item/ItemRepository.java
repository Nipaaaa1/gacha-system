package com.nipaaaa.gacha_system.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.common.Rarity;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findByBanner(Banner banner);

  List<Item> findByBannerAndRarity(Banner banner, Rarity rarity);
}
