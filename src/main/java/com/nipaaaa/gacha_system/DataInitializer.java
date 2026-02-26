package com.nipaaaa.gacha_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.banner.BannerRepository;
import com.nipaaaa.gacha_system.common.Rarity;
import com.nipaaaa.gacha_system.item.Item;
import com.nipaaaa.gacha_system.item.ItemRepository;
import com.nipaaaa.gacha_system.user.User;
import com.nipaaaa.gacha_system.user.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final BannerRepository bannerRepository;
  private final ItemRepository itemRepository;

  public DataInitializer(UserRepository userRepository,
      BannerRepository bannerRepository,
      ItemRepository itemRepository) {
    this.userRepository = userRepository;
    this.bannerRepository = bannerRepository;
    this.itemRepository = itemRepository;
  }

  @Override
  public void run(String... args) {

    User user = new User("Jamal", 10000);
    userRepository.save(user);

    Banner banner = new Banner("Standard Banner", 0.6, 70, 90);
    bannerRepository.save(banner);

    itemRepository.save(new Item("Excalibur", Rarity.SSR, 1, banner));
    itemRepository.save(new Item("Dragon Slayer", Rarity.SSR, 1, banner));

    itemRepository.save(new Item("Silver Sword", Rarity.SR, 10, banner));
    itemRepository.save(new Item("Knight Blade", Rarity.SR, 10, banner));

    itemRepository.save(new Item("Wooden Stick", Rarity.R, 50, banner));
    itemRepository.save(new Item("Rusty Sword", Rarity.R, 50, banner));
  }
}
