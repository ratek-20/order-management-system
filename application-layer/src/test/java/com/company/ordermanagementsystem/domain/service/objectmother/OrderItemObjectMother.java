package com.company.ordermanagementsystem.domain.service.objectmother;

import com.company.ordermanagementsystem.domain.model.OrderItem;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

public class OrderItemObjectMother {

    public static OrderItem aRandomOrderItem() {
        SecureRandom secureRandom = new SecureRandom();
        return new OrderItem(
                UUID.randomUUID(),
                secureRandom.nextInt(),
                BigDecimal.valueOf(secureRandom.nextDouble())
        );
    }
}
