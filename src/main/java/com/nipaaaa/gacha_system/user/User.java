package com.nipaaaa.gacha_system.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private int currency;

  protected User() {
  }

  public User(String username, int currency) {
    this.username = username;
    this.currency = currency;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public int getCurrency() {
    return currency;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
  }
}
