package com.nipaaaa.gacha_system.roll;

import java.time.LocalDateTime;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.item.Item;
import com.nipaaaa.gacha_system.user.User;

import jakarta.persistence.*;

@Entity
public class RollHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private Banner banner;

  @ManyToOne
  private Item item;

  private LocalDateTime rollTime;

  protected RollHistory() {
  }

  public RollHistory(User user, Banner banner, Item item) {
    this.user = user;
    this.banner = banner;
    this.item = item;
    this.rollTime = LocalDateTime.now();
  }
}
