package com.company.ordermanagementsystem.objectmother;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderEntityObjectMother {

    public static OrderEntity aRandomOrderEntity() {
        return new OrderEntity(UUID.randomUUID(), OrderStatus.PENDING, LocalDateTime.now(), BigDecimal.valueOf(100));
    }
}
