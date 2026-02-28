package com.nipaaaa.gacha_system.roll.dto;

import com.nipaaaa.gacha_system.common.Rarity;

public record RollItemResponse(
    String itemName,
    Rarity rarity) {
}
