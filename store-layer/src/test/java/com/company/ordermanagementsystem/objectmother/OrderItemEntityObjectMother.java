package com.company.ordermanagementsystem.objectmother;

import com.company.ordermanagementsystem.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

public class OrderItemEntityObjectMother {

    public static OrderItemEntity aRandomOrderItemEntity() {
        SecureRandom secureRandom = new SecureRandom();
        return new OrderItemEntity(UUID.randomUUID(), 10, BigDecimal.valueOf(secureRandom.nextFloat()));
    }
}
