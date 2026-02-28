package com.nipaaaa.gacha_system.roll.dto;

import java.util.List;

public record RollResponse(
        List<RollItemResponse> results,
        int remainingCurrency,
        int currentPity) {
}
