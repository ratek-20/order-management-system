package com.company.ordermanagementsystem.port.in;

import com.company.ordermanagementsystem.domain.model.OrderStatus;

import java.util.UUID;

public interface OrderMessagingInPort {
    void process(UUID orderId, OrderStatus status);
}
