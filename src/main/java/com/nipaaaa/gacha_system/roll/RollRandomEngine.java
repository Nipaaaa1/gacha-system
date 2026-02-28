package com.nipaaaa.gacha_system.roll;

import java.util.List;
import java.util.Random;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.item.Item;

public class RollRandomEngine {
  private final Random random;

  public RollRandomEngine(Random random) {
    this.random = random;
  }

  public double calculateSsrRate(Banner banner, int nextPity) {
    double baseRate = banner.getBannerSsrRate();
    int softPity = banner.getSoftPity();

    if (nextPity > softPity) {
      return baseRate;
    }

    double increasedRate = baseRate + (nextPity - (softPity - 1)) * 0.05;

    return Math.min(increasedRate, baseRate);
  }

  public boolean isHardPity(Banner banner, int nextPity) {
    return nextPity >= banner.getHardPity();
  }

  public boolean rollForSsr(double ssrRate) {
    return random.nextDouble() < ssrRate;
  }

  public Item pickByWeight(List<Item> items) {
    int totalWeight = items.stream().mapToInt(Item::getWeight).sum();

    int rand = random.nextInt(totalWeight);

    int cumulative = 0;

    for (Item item : items) {
      cumulative += item.getWeight();
      if (rand < cumulative) {
        return item;
      }
    }

    throw new IllegalStateException("Weight calculation failed");
  }

}
