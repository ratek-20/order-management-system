package com.company.ordermanagementsystem.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDTO(@NotNull UUID productId,
                           int quantity,
                           BigDecimal unitPrice) {
}
