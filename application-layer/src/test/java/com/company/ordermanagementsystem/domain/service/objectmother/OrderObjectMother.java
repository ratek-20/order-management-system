package com.company.ordermanagementsystem.domain.service.objectmother;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class OrderObjectMother {

    public static Order aRandomOrder() {
        return new Order(
                UUID.randomUUID(),
                OrderStatus.PENDING,
                LocalDateTime.now(),
                new ArrayList<>(),
                BigDecimal.valueOf(0)
        );
    }
}
