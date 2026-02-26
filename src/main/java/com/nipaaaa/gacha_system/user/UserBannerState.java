package com.nipaaaa.gacha_system.user;

import com.nipaaaa.gacha_system.banner.Banner;

import jakarta.persistence.*;

@Entity
public class UserBannerState {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private Banner banner;

  private int currentPity;

  protected UserBannerState() {
  }

  public UserBannerState(User user, Banner banner) {
    this.user = user;
    this.banner = banner;
  }

  public int getCurrentpity() {
    return currentPity;
  }

  public void increasePity() {
    this.currentPity++;
  }

  public void resetPity() {
    this.currentPity = 0;
  }
}
