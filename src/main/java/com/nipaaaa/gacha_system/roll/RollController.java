package com.nipaaaa.gacha_system.roll;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nipaaaa.gacha_system.roll.dto.RollRequest;
import com.nipaaaa.gacha_system.roll.dto.RollResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roll")
public class RollController {

  private final RollService rollService;

  public RollController(RollService rollService) {
    this.rollService = rollService;
  }

  @PostMapping("/{bannerId}")
  public RollResponse roll(@PathVariable Long bannerId, @Valid @RequestBody RollRequest request) {
    return rollService.roll(request.userId(), bannerId, request.times());
  }
}
