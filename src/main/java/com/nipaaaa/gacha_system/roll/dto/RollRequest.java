package com.nipaaaa.gacha_system.roll.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RollRequest(
    @NotNull Long userId,
    @Min(1) @Max(10) int times) {
}
