package com.nipaaaa.gacha_system.item;

import com.nipaaaa.gacha_system.banner.Banner;
import com.nipaaaa.gacha_system.common.Rarity;

import jakarta.persistence.*;

@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Rarity rarity;

  @ManyToOne
  private Banner banner;

  private int weight;

  protected Item() {
  }

  public Item(String name, Rarity rarity, Banner banner, int weight) {
    this.name = name;
    this.rarity = rarity;
    this.banner = banner;
    this.weight = weight;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public Banner getBanner() {
    return banner;
  }

  public int getWeight() {
    return weight;
  }
}
