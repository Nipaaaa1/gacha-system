package com.nipaaaa.gacha_system.roll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.banner.BannerRepository;
import com.nipaaaa.gacha_system.common.Rarity;
import com.nipaaaa.gacha_system.item.Item;
import com.nipaaaa.gacha_system.item.ItemRepository;
import com.nipaaaa.gacha_system.roll.dto.RollItemResponse;
import com.nipaaaa.gacha_system.roll.dto.RollResponse;
import com.nipaaaa.gacha_system.user.User;
import com.nipaaaa.gacha_system.user.UserBannerState;
import com.nipaaaa.gacha_system.user.UserBannerStateRepository;
import com.nipaaaa.gacha_system.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RollService {

  private final UserRepository userRepository;
  private final BannerRepository bannerRepository;
  private final UserBannerStateRepository userBannerStateRepository;
  private final ItemRepository itemRepository;
  private final RollRandomEngine rollRandomEngine;
  private final RollHistoryRepository rollHistoryRepository;
  private static final int COST_PER_ROLL = 160;

  public RollService(UserRepository userRepository, BannerRepository bannerRepository,
      UserBannerStateRepository userBannerStateRepository, ItemRepository itemRepository,
      RollRandomEngine rollRandomEngine, RollHistoryRepository rollHistoryRepository) {
    this.userRepository = userRepository;
    this.bannerRepository = bannerRepository;
    this.userBannerStateRepository = userBannerStateRepository;
    this.itemRepository = itemRepository;
    this.rollRandomEngine = rollRandomEngine;
    this.rollHistoryRepository = rollHistoryRepository;
  }

  @Transactional
  public RollResponse roll(Long userId, Long bannerId, int times) {
    User user = userRepository.findById(userId).orElseThrow();
    Banner banner = bannerRepository.findById(bannerId).orElseThrow();

    int totalCost = COST_PER_ROLL * times;

    if (user.getCurrency() < totalCost) {
      throw new IllegalStateException("Not enough currency");
    }

    UserBannerState userBannerState = userBannerStateRepository.findByUserIdAndBannerId(user.getId(), banner.getId())
        .orElseGet(() -> {
          UserBannerState newState = new UserBannerState(user, banner);

          return userBannerStateRepository.save(newState);
        });

    List<Item> allItems = itemRepository.findByBanner(banner);
    List<Item> ssrItems = itemRepository.findByBannerAndRarity(banner, Rarity.SSR);
    List<Item> nonSsrItems = allItems.stream().filter(item -> item.getRarity() != Rarity.SSR).toList();

    List<RollItemResponse> result = new ArrayList<>();

    for (int i = 0; i < times; i++) {
      int nextPity = userBannerState.getCurrentpity() + 1;

      Item obtainedItem;

      if (rollRandomEngine.isHardPity(banner, nextPity)) {
        obtainedItem = rollRandomEngine.pickByWeight(ssrItems);
        userBannerState.resetPity();
      } else {
        double ssrRate = rollRandomEngine.calculateSsrRate(banner, nextPity);

        if (rollRandomEngine.rollForSsr(ssrRate)) {
          obtainedItem = rollRandomEngine.pickByWeight(ssrItems);
          userBannerState.resetPity();
        } else {
          obtainedItem = rollRandomEngine.pickByWeight(nonSsrItems);
          userBannerState.increasePity();
        }
      }
      rollHistoryRepository.save(new RollHistory(user, banner, obtainedItem));
      result.add(new RollItemResponse(
          obtainedItem.getName(),
          obtainedItem.getRarity()));
    }

    user.setCurrency(user.getCurrency() - totalCost);

    userRepository.save(user);
    userBannerStateRepository.save(userBannerState);

    return new RollResponse(
        result,
        user.getCurrency(),
        userBannerState.getCurrentpity());

  }

}
