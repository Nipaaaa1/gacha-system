package com.nipaaaa.gacha_system.banner;

import jakarta.persistence.*;

@Entity
public class Banner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double bannerSsrRate;
  private int softPity;
  private int hardPity;

  protected Banner() {
  }

  public Banner(String name, double bannerSsrRate, int softPity, int hardPity) {
    this.name = name;
    this.bannerSsrRate = bannerSsrRate;
    this.softPity = softPity;
    this.hardPity = hardPity;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getBannerSsrRate() {
    return bannerSsrRate;
  }

  public int getSoftPity() {
    return softPity;
  }

  public int getHardPity() {
    return hardPity;
  }

}
