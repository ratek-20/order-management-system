package com.company.ordermanagementsystem.controller.objectmother;

import com.company.ordermanagementsystem.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDTOObjectMother {

    public static OrderItemDTO aRandomOrderItemDTO() {
        return new OrderItemDTO(
                UUID.randomUUID(),
                0,
                BigDecimal.valueOf(0)
        );
    }
}
