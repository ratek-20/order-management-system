package com.company.ordermanagementsystem.controller.objectmother;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.company.ordermanagementsystem.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class OrderDTOObjectMother {

    public static OrderDTO aRandomOrderDTO() {
        return new OrderDTO(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "PENDING",
                LocalDateTime.now(),
                new ArrayList<OrderItemDTO>(),
                0
        );
    }
}
